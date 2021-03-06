package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;
    private final InspectionRepository inspectionRepository;
    private final SecurityService securityService;

    public ScaffoldController(SiteRepository siteRepository, AreaRepository areaRepository, UserRepository userRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository, SecurityService securityService) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
        this.securityService = securityService;
    }
    @ModelAttribute
    public void init(Model model,
                     @PathVariable("siteId") Long siteId) {
        model.addAttribute("areaList", areaRepository.getAllBySiteId(siteId));
        model.addAttribute("scaffoldForm", new ScaffoldDto());
        model.addAttribute("site", siteRepository.getById(siteId));



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
    public String showScaffolds(@AuthenticationPrincipal CurrentUser customUser,
                                Model model,
                                @PathVariable("siteId") Long siteId) {
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
        return "app/scaffold/unapprovedscaffolds";
    }


    @GetMapping("/manage")
    public String showManageScaffolds(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }


        return "app/scaffold/managescaffolds";
    }

    @GetMapping("/find")
    public String showFindScaffold(@AuthenticationPrincipal CurrentUser customUser, Model model,
                                   @PathVariable("siteId") Long siteId) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }
        model.addAttribute("scaffoldForm", new ScaffoldDto());
        return "app/scaffold/findscaffold";
    }

    @PostMapping("/find")
    public String doFindScaffold(@PathVariable("siteId") Long siteId,
                                 @ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm,
                                 Model model,
                                 @AuthenticationPrincipal CurrentUser customUser, RedirectAttributes redirectAttrs) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "redirect:/404";
        }

        Scaffold myScaffold = scaffoldRepository.findByScaffoldId(scaffoldForm.getScaffoldId());
        if (myScaffold == null) {
            redirectAttrs.addAttribute("info", "noSuchScaffold");
            return "redirect:/app/site/{siteId}/scaffold/find";
        }
        redirectAttrs.addAttribute("scaffId", myScaffold.getId());
        return "redirect:/app/site/{siteId}/scaffold/{scaffId}/detailsscaffold";

    }

    @GetMapping("/group")
    public String showGroupScaffold(@AuthenticationPrincipal CurrentUser customUser, Model model,
                                   @PathVariable("siteId") Long siteId) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }
        model.addAttribute("areaList", areaRepository.getAllBySiteId(siteId));
        model.addAttribute("scaffoldForm", new ScaffoldDto());
        return "app/scaffold/groupscaffold";
    }

    @PostMapping("/group")
    public String doGroupScaffold(@PathVariable("siteId") Long siteId,
                                 @ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm,
                                 @AuthenticationPrincipal CurrentUser customUser,
                                  RedirectAttributes redirectAttrs) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "redirect:/404";
        }

        Area myArea = areaRepository.getAreaByNameAndSiteId(scaffoldForm.getArea(), siteId);
        if (myArea == null) {
            redirectAttrs.addAttribute("info", "noSuchScaffold");
            return "redirect:/app/site/{siteId}/scaffold/group";
        }
        redirectAttrs.addAttribute("areaId", myArea.getId());
        return "redirect:/app/site/{siteId}/area/{areaId}/";

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

        model.addAttribute("inspectionList", inspectionList);
        model.addAttribute("scaff", myScaffold);
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

//
//    @GetMapping("/showscaffoldsbygroup")
//    public String showScaffolds(@AuthenticationPrincipal CurrentUser customUser,
//                                Model model,
//                                @PathVariable("siteId") Long siteId) {
//        User entityUser = customUser.getUser();
//        User myUser = userService.findByUserName(entityUser.getUsername());
//
//        if (!securityService.hasAccess(myUser.getId(), siteId)) {
//            return "admin/403";
//        }
//
//        model.addAttribute("scaffoldList", scaffoldRepository.getAllBySiteId(siteId));
//        return "app/scaffold/showscaffolds";
//
//    }
}
