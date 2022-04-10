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


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/app")
public class AppController {
    private final SiteRepository siteRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;


    public AppController(ScaffoldRepository scaffoldRepository, AreaRepository areaRepository, SiteRepository siteRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService) {
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.scaffoldRepository = scaffoldRepository;
    }

    @GetMapping("/{siteId}")
    public String viewDashboard(HttpServletResponse response, @AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("siteId") Long siteId) {
        // create and add cookie over chosen siteid



        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        if(siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            Cookie theCookie = new Cookie("siteId", String.valueOf(siteId));
            theCookie.setMaxAge(60*60*1);
            response.addCookie(theCookie);
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


