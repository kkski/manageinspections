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
        Cookie theCookie = new Cookie("siteId", String.valueOf(siteId));
        theCookie.setMaxAge(60*60*1);
        response.addCookie(theCookie);


        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        if (myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("chosenSite", siteRepository.getById(siteId));
            model.addAttribute("inspectorName", myUser.getInspector().getFirstName());
            return "app/dashboard";
        } else {
            return "admin/403";
        }
    }


    @GetMapping("/scaffold/add")
    public String addScaffoldForm(@AuthenticationPrincipal CurrentUser customUser, Model model, HttpServletRequest request) {
        CookieUtil cookieUtil = new CookieUtil();
        Long siteId = cookieUtil.getSiteIdCookieValue(request);
        model.addAttribute("scaffoldForm", new ScaffoldDto());
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        if (myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("areaList", siteRepository.getById(siteId).getAreasList());
            return "app/scaffold/addscaffold";
        } else {
            return "admin/403";
        }

    }

    @PostMapping("/scaffold/add")
    public RedirectView addScaffold(HttpServletRequest request, @ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        CookieUtil cookieUtil = new CookieUtil();

        Scaffold myScaffold = new Scaffold();
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Site chosenSite = siteRepository.getById(cookieUtil.getSiteIdCookieValue(request));

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
        return new RedirectView("/app/scaffold/showscaffolds");

    }

    @GetMapping("/scaffold/showscaffolds")
    public String showScaffolds(@AuthenticationPrincipal CurrentUser customUser, Model model, HttpServletRequest request) {
        CookieUtil cookieUtil = new CookieUtil();

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("scaffoldList", scaffoldRepository.getAllBySite(siteRepository.getById(cookieUtil.getSiteIdCookieValue(request))));
        return "app/scaffold/showscaffolds";
    }

    @GetMapping("/scaffold/detailsscaffold/{scaffId}")
    public String showScaffoldDetails(@AuthenticationPrincipal CurrentUser customUser, Model model, @PathVariable("scaffId") Long scaffId) {
        Scaffold myScaffold = scaffoldRepository.getById(scaffId);
        model.addAttribute("scaff", myScaffold);
        return "app/scaffold/detailsscaffold";
    }




}
// Spring web bin session scope
//@QueryParam


