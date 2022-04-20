package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.UserService;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/app")
public class AppController {

    private final SiteRepository siteRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;
    private final InspectionRepository inspectionRepository;

    public AppController(SiteRepository siteRepository, AreaRepository areaRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
    }


    @GetMapping("/site")
    public String viewSiteChoice(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("sitesList", myUser.getInspector().getSitesList());
        model.addAttribute("inspectorName", myUser.getInspector().getFirstName());
        return "app/site/sitechoice";
    }

    @GetMapping("/site/add")
    public String addSite(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("siteForm", new Site());
        return "app/site/siteadd";
    }

    @PostMapping("/site/add")
    public RedirectView addSitePost(@ModelAttribute("siteForm") Site siteForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        siteRepository.save(siteForm);
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        myUser.getInspector().getSitesList().add(siteForm);
        userRepository.save(myUser);
        return new RedirectView("/app/site");

    }


    @GetMapping("/site/{siteId}")
    public String viewDashboard(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if(siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {

            model.addAttribute("chosenSite", siteRepository.getById(siteId));

            model.addAttribute("inspectorName", myUser.getInspector().getFirstName());

            return "app/dashboard";

        } else {
            return "admin/404";
        }
    }



}
// Spring web bin session scope
//@QueryParam


