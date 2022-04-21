package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.dtos.SiteDto;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.SecurityService;
import pl.coderslab.manageinspections.service.UserService;

import javax.validation.Valid;

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
    private final SecurityService securityService;

    public AppController(SiteRepository siteRepository,
                         AreaRepository areaRepository,
                         UserRepository userRepository,
                         InspectorRepository inspectorRepository,
                         UserService userService,
                         ScaffoldRepository scaffoldRepository,
                         InspectionRepository inspectionRepository,
                         SecurityService securityService) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
        this.securityService = securityService;
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
        model.addAttribute("siteForm", new SiteDto());
        return "app/site/siteadd";
    }

    @PostMapping("/site/add")
    public String addSitePost(@Valid @ModelAttribute("siteForm") SiteDto siteForm,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal CurrentUser customUser

    ) {
        if (bindingResult.hasErrors()) {
            return "app/site/siteadd";
        }

        Site toSave = new Site();
        toSave.setName(siteForm.getName());
        toSave.setAddress(siteForm.getAddress());
        siteRepository.save(toSave);
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        myUser.getInspector().getSitesList().add(toSave);
        userRepository.save(myUser);

        return "redirect:/app/site";

    }


    @GetMapping("/site/{siteId}")
    public String viewDashboard(@AuthenticationPrincipal CurrentUser customUser,
                                Model model,
                                @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        Inspection lastAddedInspection = inspectionRepository.getFirstBySiteId(siteId);

        if (lastAddedInspection != null) {
            Scaffold lastInspectedScaffold = lastAddedInspection.getScaffold();
            Area lastInspectedArea = lastInspectedScaffold.getArea();
            model.addAttribute("lastAddedInspection", lastAddedInspection);
            model.addAttribute("lastInspectedScaffold", lastInspectedScaffold);
            model.addAttribute("lastInspectedArea", lastInspectedArea);
        }
        model.addAttribute("scaffoldListCount", scaffoldRepository.getAllBySiteId(siteId).size());
        model.addAttribute("approvedScaffoldsCount", scaffoldRepository.getAllBySiteIdAndApproval(siteId, true).size());
        model.addAttribute("unapprovedScaffoldsCount", scaffoldRepository.getAllBySiteIdAndApproval(siteId, false).size());
        model.addAttribute("chosenSite", siteRepository.getById(siteId));
        model.addAttribute("inspectorName", myUser.getInspector().getFirstName());

        return "app/dashboard";


    }


}
// Spring web bin session scope
//@QueryParam


