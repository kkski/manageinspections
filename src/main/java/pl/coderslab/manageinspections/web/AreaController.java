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
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app/site/{siteId}/area")
public class AreaController {

    private UserService userService;
    private AreaRepository areaRepository;
    private UserRepository userRepository;
    private SiteRepository siteRepository;
    private ScaffoldRepository scaffoldRepository;

    public AreaController(ScaffoldRepository scaffoldRepository, SiteRepository siteRepository, UserService userService, AreaRepository areaRepository, UserRepository userRepository) {
        this.userService = userService;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.siteRepository = siteRepository;
        this.scaffoldRepository = scaffoldRepository;
    }

    @GetMapping("/add")
    public String addAreaForm(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("areaForm", new Area());
            return "app/area/addarea";
        } else {
            return "admin/403";
        }
    }

    @PostMapping("/add")
    public RedirectView addArea(@ModelAttribute("areaForm") Area areaForm, Model model, @AuthenticationPrincipal CurrentUser customUser, @PathVariable("siteId") Long siteId) {

        Area myArea = new Area();
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        myArea.setName(areaForm.getName());
        myArea.setSite(siteRepository.getById(siteId));
        areaRepository.save(myArea);

        siteRepository.getById(siteId).getAreasList().add(myArea);
        userRepository.save(myUser);
        return new RedirectView("/app/site/{siteId}/area/showareas");

    }

    @GetMapping("/showareas")
    public String showAreas(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("areaList", siteRepository.getById(siteId).getAreasList());
            return "app/area/showareas";
        } else {
            return "admin/403";
        }
    }



    @GetMapping("/{areaId}/deletearea")
    public String showAreaToDelete(@AuthenticationPrincipal CurrentUser customUser,
                                   Model model,
                                   @PathVariable("siteId") Long siteId,
                                   @PathVariable("areaId") Long areaId) {
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

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            Area areaToRemove = areaRepository.getById(areaId);
            List<Long> idsOfScaffoldsFromAreaToRemove = areaToRemove.getScaffoldList().stream().map(Scaffold::getId).collect(Collectors.toList());
            scaffoldRepository.deleteAllById(idsOfScaffoldsFromAreaToRemove);
            siteRepository.getById(siteId).getAreasList().remove(areaToRemove);
            areaRepository.delete(areaToRemove);
            return new RedirectView("/app/site/{siteId}/area/showareas");
        } else {
            return new RedirectView("/403");
        }
    }

    @GetMapping("/{areaId}/editarea")
    public String editAreaForm(@AuthenticationPrincipal CurrentUser customUser,
                               Model model,
                               @PathVariable("siteId") Long siteId,
                               @PathVariable("areaId") Long areaId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("area", areaRepository.getById(areaId));
            model.addAttribute("areaForm", new Area());
            return "app/area/editarea";
        } else {
            return "admin/403";
        }
    }

    @PostMapping("/{areaId}/editarea")
    public RedirectView editArea(@ModelAttribute("areaForm") Area areaForm,
                                 Model model,
                                 @AuthenticationPrincipal CurrentUser customUser,
                                 @PathVariable("siteId") Long siteId,
                                 @PathVariable("areaId") Long areaId)
    {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {

            Area areaToSave = areaRepository.getById(areaId);
            areaToSave.setName(areaForm.getName());
            areaRepository.save(areaToSave);

            return new RedirectView("/app/site/{siteId}/area/showareas");

        } else {

            return new RedirectView("/403");
        }


    }

}