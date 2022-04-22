package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.dtos.InspectionDto;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.model.ScaffoldDto;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.SecurityService;
import pl.coderslab.manageinspections.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/app/site/{siteId}/scaffold")
public class ScaffoldController {
    private final SiteRepository siteRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;
    private final InspectionRepository inspectionRepository;
    private SecurityService securityService;

    public ScaffoldController(SiteRepository siteRepository, AreaRepository areaRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository, SecurityService securityService) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
        this.securityService = securityService;
    }
    @ModelAttribute
    public void init(Model model,
                     @PathVariable("siteId") Long siteId) {
        model.addAttribute("areaList", areaRepository.getAllBySiteId(siteId));

    }

    @GetMapping("/add")
    public String addScaffoldForm(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model,
                                  @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("scaffoldForm", new ScaffoldDto());
        model.addAttribute("areaList", siteRepository.getById(siteId).getAreasList());
        return "app/scaffold/addscaffold";

    }

    @PostMapping("/add")
    public String addScaffold(@PathVariable("siteId") Long siteId,
                              @Valid @ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal CurrentUser customUser) {
        if (bindingResult.hasErrors()) {
            return "app/scaffold/addscaffold";
        }

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "redirect:/404";
        }

        Scaffold myScaffold = new Scaffold();
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

        userRepository.save(myUser);

        return "redirect:/app/site/{siteId}/scaffold/showscaffolds";

    }

    @GetMapping("/showscaffolds")
    public String showScaffolds(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("scaffoldList", scaffoldRepository.getAllBySiteId(siteId));
        return "app/scaffold/showscaffolds";

    }

    @GetMapping("/showunapproved")
    public String showUnapprovedScaffolds(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }
        List<Scaffold> unapprovedScaffolds = scaffoldRepository.getAllBySiteIdAndApprovalOrderByAreaName(siteId, false);
        model.addAttribute("unapprovedScaffolds", unapprovedScaffolds);
        return "app/scaffold/showunapproved";
    }

    @GetMapping("/{scaffId}/detailsscaffold")
    public String showScaffoldDetails(@AuthenticationPrincipal CurrentUser customUser,
                                      Model model,
                                      @PathVariable("scaffId") Long scaffId,
                                      @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        Scaffold myScaffold = scaffoldRepository.getById(scaffId);
        List<Inspection> inspectionList = inspectionRepository.getAllByScaffoldId(scaffId);

        model.addAttribute("scaff", myScaffold);
        model.addAttribute("inspectionList", inspectionList);
        model.addAttribute("siteId", siteId);
        return "app/scaffold/detailsscaffold";
    }


    @GetMapping("/{scaffId}/detailsscaffold/delete")
    public String showScaffoldToDelete(@AuthenticationPrincipal CurrentUser customUser,
                                       Model model,
                                       @PathVariable("siteId") Long siteId,
                                       @PathVariable("scaffId") Long scaffId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("site", siteRepository.getById(siteId));
        model.addAttribute("scaff", scaffoldRepository.getById(scaffId));
        return "app/scaffold/deletescaffold";
    }

    @GetMapping("/{scaffId}/detailsscaffold/delete/confirm")
    public RedirectView deleteScaffold(@AuthenticationPrincipal CurrentUser customUser,
                                   @PathVariable("siteId") Long siteId,
                                   @PathVariable("scaffId") Long scaffId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return new RedirectView("/404");
        }

        Scaffold scaffoldToRemove = scaffoldRepository.getById(scaffId);
        siteRepository.getById(siteId).getScaffoldList().remove(scaffoldToRemove);
        scaffoldRepository.delete(scaffoldToRemove);
        return new RedirectView("/app/site/{siteId}/scaffold/showscaffolds");

    }

    @GetMapping("/{scaffId}/detailsscaffold/edit")
    public String editScaffoldForm(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model,
                                   @PathVariable("siteId") Long siteId,
                                   @PathVariable("scaffId") Long scaffId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("scaffoldForm", new ScaffoldDto());
        model.addAttribute("site", siteRepository.getById(siteId));
        model.addAttribute("areaList", siteRepository.getById(siteId).getAreasList());
        model.addAttribute("scaff", scaffoldRepository.getById(scaffId));
        return "app/scaffold/editscaffold";

    }

    @PostMapping("/{scaffId}/detailsscaffold/edit")
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

        return "redirect:app/site/${siteId}/scaffold/showscaffolds";


    }
}
