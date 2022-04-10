package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.dtos.InspectionDto;
import pl.coderslab.manageinspections.model.*;
import pl.coderslab.manageinspections.model.ScaffoldDto;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/app/inspection")
public class InspectionController {
    private final SiteRepository siteRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;
    private final InspectionRepository inspectionRepository;

    public InspectionController(SiteRepository siteRepository, AreaRepository areaRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
    }

    @GetMapping("/add/{scaffId}")
    public String addInspectionForm(@PathVariable("scaffId") Long scaffId, @AuthenticationPrincipal CurrentUser customUser, Model model, HttpServletRequest request) {
        CookieUtil cookieUtil = new CookieUtil();
        Long siteId = cookieUtil.getSiteIdCookieValue(request);

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (siteRepository.existsById(siteId) && myUser.getInspector().getSitesList().contains(siteRepository.getById(siteId))) {
            model.addAttribute("scaff", scaffoldRepository.getById(scaffId));
            model.addAttribute("inspectionForm", new InspectionDto());
            return "app/inspection/addinspection";
        } else {
            return "admin/403";
        }

    }

    @PostMapping("/add/{scaffId}")
    public RedirectView addInspection(@PathVariable("scaffId") Long scaffId, HttpServletRequest request, @ModelAttribute("inspectionForm") InspectionDto inspectionForm, Model model, @AuthenticationPrincipal CurrentUser customUser) {
        CookieUtil cookieUtil = new CookieUtil();

        Inspection myInspection = new Inspection();
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        Scaffold chosenScaffold = scaffoldRepository.getById(scaffId);

        myInspection.setScaffold(chosenScaffold);
        LocalDate dateOfInspecting = LocalDate.parse(inspectionForm.getDateOfInspection(), formatter);
        myInspection.setDateOfInspection(dateOfInspecting);
        myInspection.setDateOfCreation(LocalDateTime.now());
        myInspection.setInspector(myUser.getInspector());
        myInspection.setInspectionMessage(inspectionForm.getInspectionMessage());
        myInspection.setApproved(inspectionForm.getApproved());

        inspectionRepository.save(myInspection);
        chosenScaffold.getInspectionsList().add(myInspection);
        scaffoldRepository.save(chosenScaffold);
        return new RedirectView("/app/scaffold/showscaffolds");

    }

}