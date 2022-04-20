package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.model.ScaffoldDto;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

    public ScaffoldController(SiteRepository siteRepository, AreaRepository areaRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
    }

    @GetMapping("/add")
    public String addScaffoldForm(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model,
                                  @PathVariable("siteId") Long siteId) {

        model.addAttribute("scaffoldForm", new ScaffoldDto());
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("areaList", siteRepository.getById(siteId).getAreasList());
            return "app/scaffold/addscaffold";
        } else {
            return "admin/403";
        }

    }

    @PostMapping("/add")
    public RedirectView addScaffold(@PathVariable("siteId") Long siteId,
                                    @ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm,
                                    @AuthenticationPrincipal CurrentUser customUser) {

        Scaffold myScaffold = new Scaffold();
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
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
        return new RedirectView("/app/site/{siteId}/scaffold/showscaffolds");

    }

    @GetMapping("/showscaffolds")
    public String showScaffolds(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("scaffoldList", scaffoldRepository.getAllBySiteId(siteId));
            return "app/scaffold/showscaffolds";
        } else {
            return "admin/403";
        }
    }

    @GetMapping("/{scaffId}/detailsscaffold")
    public String showScaffoldDetails(@AuthenticationPrincipal CurrentUser customUser,
                                      Model model,
                                      @PathVariable("scaffId") Long scaffId,
                                        @PathVariable("siteId") Long siteId)

    {
        Scaffold myScaffold = scaffoldRepository.getById(scaffId);
        List<Inspection> inspectionList = inspectionRepository.getAllByScaffoldId(scaffId);

        model.addAttribute("scaff", myScaffold);
        model.addAttribute("inspectionList", inspectionList);
        model.addAttribute("siteId", siteId);
        return "app/scaffold/detailsscaffold";
    }
}
