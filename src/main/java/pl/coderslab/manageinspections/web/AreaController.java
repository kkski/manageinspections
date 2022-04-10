package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.model.Area;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
@RequestMapping("/app/{siteId}/area")
public class AreaController {

    private UserService userService;
    private AreaRepository areaRepository;
    private UserRepository userRepository;
    private SiteRepository siteRepository;
    private CookieUtil cookieUtil;
    public AreaController(SiteRepository siteRepository, UserService userService, AreaRepository areaRepository, UserRepository userRepository) {
        this.userService = userService;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.siteRepository = siteRepository;
        this.cookieUtil = cookieUtil;
    }

    @GetMapping("/add")
    public String addAreaForm(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("areaForm", new Area());
        return "app/area/addarea";
    }

    @PostMapping("/add")
    public RedirectView addArea(HttpServletRequest request, @ModelAttribute("areaForm") Area areaForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        Long siteId = cookieUtil.getSiteIdCookieValue(request);
        Area myArea = new Area();
        myArea.setName(areaForm.getName());
        myArea.setSite(siteRepository.getById(siteId));

        areaRepository.save(myArea);


        siteRepository.getById(siteId).getAreasList().add(myArea);
        userRepository.save(myUser);
        return new RedirectView("/app/area/showareas");

    }

    @GetMapping("/showareas")
    public String showAreas(@AuthenticationPrincipal CurrentUser customUser, Model model, Long siteId, HttpServletRequest request) {


        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        // if myuser nie ma takiego zasobu to 404, jak ma to wyswietlaj
        model.addAttribute("areaList", siteRepository.getById(cookieUtil.getSiteIdCookieValue(request)).getAreasList());
        return "app/area/showareas";
    }
}