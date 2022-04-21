package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.SecurityService;
import pl.coderslab.manageinspections.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/app/site/{siteId}/area")
public class AreaController {

    private UserService userService;
    private AreaRepository areaRepository;
    private UserRepository userRepository;
    private SiteRepository siteRepository;
    private ScaffoldRepository scaffoldRepository;
    private SecurityService securityService;

    public AreaController(ScaffoldRepository scaffoldRepository, SiteRepository siteRepository, UserService userService, AreaRepository areaRepository, UserRepository userRepository, SecurityService securityService) {
        this.userService = userService;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.siteRepository = siteRepository;
        this.scaffoldRepository = scaffoldRepository;
        this.securityService = securityService;
    }

    @GetMapping("/add")
    public String addAreaForm(@AuthenticationPrincipal CurrentUser customUser,
                              Model model,
                              @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("areaForm", new Area());
        return "app/area/addarea";

    }

    @PostMapping("/add")
    public String addArea(@Valid @ModelAttribute("areaForm") Area areaForm,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal CurrentUser customUser,
                          @PathVariable("siteId") Long siteId

    ) {
        if (bindingResult.hasErrors()) {
            return "app/area/addarea";
        }

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "redirect:/404";
        }


        Area myArea = new Area();
        myArea.setName(areaForm.getName());
        myArea.setSite(siteRepository.getById(siteId));

        areaRepository.save(myArea);

        siteRepository.getById(siteId).getAreasList().add(myArea);
        userRepository.save(myUser);
        return ("redirect:/app/site/{siteId}/area/showareas");

    }

    @GetMapping("/showareas")
    public String showAreas(@AuthenticationPrincipal CurrentUser customUser,
                            Model model,
                            @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("areaList", siteRepository.getById(siteId).getAreasList());
        return "app/area/showareas";

    }


    @GetMapping("/{areaId}/deletearea")
    public String showAreaToDelete(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model,
                                   @PathVariable("siteId") Long siteId,
                                   @PathVariable("areaId") Long areaId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("site", siteRepository.getById(siteId));
        model.addAttribute("area", areaRepository.getById(areaId));
        return "app/area/deletearea";
    }

    @GetMapping("/{areaId}/deletearea/confirm")
    public RedirectView deleteArea(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model,
                                   @PathVariable("siteId") Long siteId,
                                   @PathVariable("areaId") Long areaId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return new RedirectView("/404");
        }

        Area areaToRemove = areaRepository.getById(areaId);
        siteRepository.getById(siteId).getAreasList().remove(areaToRemove);
        areaRepository.delete(areaToRemove);
        return new RedirectView("/app/site/{siteId}/area/showareas");

    }

    @GetMapping("/{areaId}/editarea")
    public String editAreaForm(@AuthenticationPrincipal CurrentUser customUser,
                               Model model,
                               @PathVariable("siteId") Long siteId,
                               @PathVariable("areaId") Long areaId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        model.addAttribute("area", areaRepository.getById(areaId));
        model.addAttribute("areaForm", new Area());
        return "app/area/editarea";

    }

    @PostMapping("/{areaId}/editarea")
    public String editArea(@Valid @ModelAttribute("areaForm") Area areaForm,
                           BindingResult bindingResult,
                           Model model,
                           @AuthenticationPrincipal CurrentUser customUser,
                           @PathVariable("siteId") Long siteId,
                           @PathVariable("areaId") Long areaId
                           ) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        if (bindingResult.hasErrors()) {
            return "app/area/editarea";
        }


        Area areaToSave = areaRepository.getById(areaId);
        areaToSave.setName(areaForm.getName());
        areaRepository.save(areaToSave);

        return "redirect:app/dashboard";


    }

}