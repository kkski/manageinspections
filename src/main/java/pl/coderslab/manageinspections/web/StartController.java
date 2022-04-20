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
@RequestMapping("/start")
public class StartController {
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;

    public StartController(SiteRepository siteRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService) {
        this.siteRepository = siteRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public RedirectView viewLandingPageOrRegisterInspector(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        if (myUser.hasInspector()) {
            return new RedirectView("/app/site");
        }
        return new RedirectView("start/inspector/registerinspector");
    }

    @GetMapping("/inspector/registerinspector")
    public String registerInspector(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("inspectorForm", new Inspector());
        return "app/inspector/registerinspector";
    }

    @PostMapping("/inspector/registerinspector")
    public RedirectView registerInspectorPost(@ModelAttribute("inspectorForm") Inspector inspectorForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        inspectorRepository.save(inspectorForm);
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        myUser.setInspector(inspectorForm);
        userRepository.save(myUser);
        return new RedirectView("/start");

    }

}
