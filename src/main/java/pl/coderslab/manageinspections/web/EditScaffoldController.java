package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.model.ScaffoldDto;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.SecurityService;
import pl.coderslab.manageinspections.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/app/site/{siteId}/scaffold/{scaffId}/detailsscaffold/edit")
public class EditScaffoldController {

        private final SiteRepository siteRepository;
        private final AreaRepository areaRepository;
        private final UserService userService;
        private final ScaffoldRepository scaffoldRepository;
        private final SecurityService securityService;

    public EditScaffoldController(SiteRepository siteRepository, AreaRepository areaRepository, UserService userService, ScaffoldRepository scaffoldRepository, SecurityService securityService) {
            this.siteRepository = siteRepository;
            this.areaRepository = areaRepository;
            this.userService = userService;
            this.scaffoldRepository = scaffoldRepository;
            this.securityService = securityService;
    }

    @ModelAttribute
    public void init(Model model,
                     @PathVariable("siteId") Long siteId,
                     @PathVariable("scaffId") Long scaffId) {
        model.addAttribute("areaList", areaRepository.getAllBySiteId(siteId));
        model.addAttribute("scaffoldForm", new ScaffoldDto());
        model.addAttribute("site", siteRepository.getById(siteId));
        model.addAttribute("scaff", scaffoldRepository.getById(scaffId));


    }

    @GetMapping("")
    public String editScaffoldForm(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model,
                                   @PathVariable("siteId") Long siteId,
                                   @PathVariable("scaffId") Long scaffId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        return "app/scaffold/editscaffold";

    }

    @PostMapping("")
    public String editScaffold(@Valid @ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal CurrentUser customUser,
                               @PathVariable("siteId") Long siteId,
                               @PathVariable("scaffId") Long scaffId
    ) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        if (bindingResult.hasErrors()) {
            return "app/scaffold/editscaffold";
        }


        Scaffold myScaffold = scaffoldRepository.getById(scaffId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Site chosenSite = siteRepository.getById(siteId);
        Area chosenArea = areaRepository.getAreaByNameAndSiteId(scaffoldForm.getArea(), chosenSite.getId());

        myScaffold.setSite(chosenSite);
        myScaffold.setName(scaffoldForm.getName());
        myScaffold.setErectorName(scaffoldForm.getErectorName());
        myScaffold.setForemanName(scaffoldForm.getForemanName());
        myScaffold.setScaffoldGrade(scaffoldForm.getScaffoldGrade());
        myScaffold.setArea(chosenArea);
        LocalDate dateOfCreation = LocalDate.parse(scaffoldForm.getDateOfErection(), formatter);
        myScaffold.setDateOfErection(dateOfCreation);
        myScaffold.setScaffoldId(scaffoldForm.getScaffoldId());

        scaffoldRepository.save(myScaffold);

        return "redirect:app/site/{siteId}/scaffold/showscaffolds";


    }

}
