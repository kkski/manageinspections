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

    @GetMapping("")
    public String viewDashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("chosenSite", myUser.getInspector().getChosenSite());
        model.addAttribute("inspectorName", myUser.getInspector().getFirstName());
        return "app/dashboard";
    }


    @GetMapping("/scaffold/add")
    public String addScaffoldForm(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        model.addAttribute("scaffoldForm", new ScaffoldDto());
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("areaList", myUser.getInspector().getChosenSite().getAreasList());
        return "app/scaffold/addscaffold";
    }

    @PostMapping("/scaffold/add")
    public RedirectView addScaffold(@ModelAttribute("scaffoldForm") ScaffoldDto scaffoldForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        Scaffold myScaffold = new Scaffold();
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Site chosenSite = myUser.getInspector().getChosenSite();

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
    public String showScaffolds(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        model.addAttribute("scaffoldList", scaffoldRepository.getAllBySite(myUser.getInspector().getChosenSite()));
        return "app/scaffold/showscaffolds";
    }

    @PostMapping("/scaffold/showscaffolds")
    public RedirectView goToDetails(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        return new RedirectView("app/scaffold/detailsscaffold");
    }



}

