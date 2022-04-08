package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public RedirectView viewLandingPageOrRegisterInspector(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        if (myUser.hasInspector()) {
            return new RedirectView("/start/2");
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
    @GetMapping("/2")
    public String viewSiteChoice(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("sitesList", myUser.getInspector().getSitesList());
        model.addAttribute("chosenSite", new Site());
        model.addAttribute("inspectorName", myUser.getInspector().getFirstName());
        return "app/site/sitechoice";
    }
    @PostMapping("/2")
    public RedirectView goToDashboard(@ModelAttribute("chosenSite") Site chosenSite, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        Site mySite = siteRepository.findSiteByName(chosenSite.getName());
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        myUser.getInspector().setChosenSite(mySite);
        userRepository.save(myUser);
        return new RedirectView("/app");

    }

    @GetMapping("/2/site/add")
    public String addSite(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("siteForm", new Site());
        return "app/site/siteadd";
    }

    @PostMapping("/2/site/add")
    public RedirectView addSitePost(@ModelAttribute("siteForm") Site siteForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        siteRepository.save(siteForm);
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        myUser.getInspector().getSitesList().add(siteForm);
        userRepository.save(myUser);
        return new RedirectView("/start/2");

    }
}
