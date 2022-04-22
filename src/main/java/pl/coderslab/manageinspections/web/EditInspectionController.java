package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.manageinspections.dtos.InspectionDto;
import pl.coderslab.manageinspections.model.Inspection;
import pl.coderslab.manageinspections.model.Scaffold;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.*;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.SecurityService;
import pl.coderslab.manageinspections.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/app/site/{siteId}/scaffold/{scaffId}/inspection/{inspectionId}/edit")
public class EditInspectionController {
    private final SiteRepository siteRepository;
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;
    private final InspectionRepository inspectionRepository;
    private final SecurityService securityService;

    public EditInspectionController(SiteRepository siteRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository, SecurityService securityService) {
        this.siteRepository = siteRepository;
        this.userService = userService;
        this.scaffoldRepository = scaffoldRepository;
        this.inspectionRepository = inspectionRepository;
        this.securityService = securityService;
    }

    @ModelAttribute
    public void init(Model model,
                     @PathVariable("scaffId") Long scaffId,
                     @PathVariable("siteId") Long siteId,
                     @PathVariable("inspectionId") Long inspectionId) {
        model.addAttribute("scaff", scaffoldRepository.getById(scaffId));
        model.addAttribute("inspectionForm", new InspectionDto());
        model.addAttribute("site", siteRepository.getById(siteId));
        model.addAttribute("inspection", inspectionRepository.getById(inspectionId));
    }


    @GetMapping("")
    public String editInspectionForm(@AuthenticationPrincipal CurrentUser customUser,
                                     Model model,
                                     @PathVariable("siteId") Long siteId,
                                     @PathVariable("scaffId") Long scaffId,
                                     @PathVariable("inspectionId") Long inspectionId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());




        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        return "app/inspection/editinspection";

    }

    @PostMapping("")
    public String editInspection(@Valid @ModelAttribute("inspectionForm") InspectionDto inspectionForm,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal CurrentUser customUser,
                                 @PathVariable("siteId") Long siteId,
                                 @PathVariable("scaffId") Long scaffId,
                                 @PathVariable("inspectionId") Long inspectionId
    ) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        if (bindingResult.hasErrors()) {
            return "app/inspection/editinspection";
        }


        Inspection myInspection = inspectionRepository.getById(inspectionId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Scaffold chosenScaffold = scaffoldRepository.getById(scaffId);

        myInspection.setScaffold(chosenScaffold);
        LocalDate dateOfInspecting = LocalDate.parse(inspectionForm.getDateOfInspection(), formatter);
        myInspection.setDateOfInspection(dateOfInspecting);
        myInspection.setDateOfCreation(LocalDateTime.now());
        myInspection.setInspector(myUser.getInspector());
        myInspection.setInspectionMessage(inspectionForm.getInspectionMessage());
        myInspection.setApproved(inspectionForm.getApproved());
        myInspection.setSite(siteRepository.getById(siteId));

        inspectionRepository.save(myInspection);

        Inspection lastInspection = inspectionRepository.getFirstByScaffoldIdOrderByDateOfInspectionDesc(scaffId);
        if (myInspection == lastInspection) {
            chosenScaffold.setApproval(myInspection.isApproved());
        } else if (myInspection.getDateOfInspection().isAfter(lastInspection.getDateOfInspection())) {
            if (myInspection.getDateOfInspection().isAfter(LocalDate.now().minusDays(7)) && myInspection.isApproved()) {
                chosenScaffold.setApproval(true);
            } else {
                chosenScaffold.setApproval(false);
            }
        } else {
            chosenScaffold.setApproval(lastInspection.isApproved());
        }



        scaffoldRepository.save(chosenScaffold);
        siteRepository.getById(siteId).getInspectionList().add(myInspection);


        return "redirect:/app/site/{siteId}/scaffold/{scaffId}/detailsscaffold";


    }

}
