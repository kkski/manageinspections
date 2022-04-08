package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.model.Inspector;
import pl.coderslab.manageinspections.model.Site;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.InspectorRepository;
import pl.coderslab.manageinspections.repository.SiteRepository;
import pl.coderslab.manageinspections.repository.UserRepository;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.UserService;

@Controller
@RequestMapping("/app")
public class AppController {
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;

    public AppController(SiteRepository siteRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService) {
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.siteRepository = siteRepository;
    }

    @GetMapping("")
    public String viewDashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("chosenSite", myUser.getInspector().getChosenSite());
        model.addAttribute("inspectorName", myUser.getInspector().getFirstName());
        return "app/dashboard";
    }

}

