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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/app/site/{siteId}/scaffold/{scaffId}/inspection")
public class InspectionController {
    private final SiteRepository siteRepository;
    private final AreaRepository areaRepository;
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;
    private final ScaffoldRepository scaffoldRepository;
    private final InspectionRepository inspectionRepository;
    private final SecurityService securityService;

    public InspectionController(SiteRepository siteRepository, AreaRepository areaRepository, UserRepository userRepository, InspectorRepository inspectorRepository, UserService userService, ScaffoldRepository scaffoldRepository, InspectionRepository inspectionRepository, SecurityService securityService) {
        this.siteRepository = siteRepository;
        this.areaRepository = areaRepository;
        this.userRepository = userRepository;
        this.inspectorRepository = inspectorRepository;
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

    @GetMapping("/add")
    public String addInspectionForm(@PathVariable("scaffId") Long scaffId,
                                    @PathVariable("siteId") Long siteId,
                                    @AuthenticationPrincipal CurrentUser customUser,
                                    Model model
    ) {
        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }


        return "app/inspection/addinspection";


    }

    @PostMapping("/add")
    public String addInspection(@Valid @ModelAttribute("inspectionForm") InspectionDto inspectionForm,
                                BindingResult bindingResult,
                                @PathVariable("scaffId") Long scaffId,
                                @PathVariable("siteId") Long siteId,
                                @AuthenticationPrincipal CurrentUser customUser,
                                Model model,
                                final RedirectAttributes redirectAttributes) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "redirect:/404";
        }

        if (bindingResult.hasErrors()) {

            return "app/inspection/addinspection";
        }

        Inspection myInspection = new Inspection();

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


        if (myInspection.getDateOfInspection().isAfter(LocalDate.now().minusDays(7)) && myInspection.isApproved() == true) {
            chosenScaffold.setApproval(true);
        } else {
            chosenScaffold.setApproval(false);
        }


        scaffoldRepository.save(chosenScaffold);
        siteRepository.getById(siteId).getInspectionList().add(myInspection);
        return "redirect:/app/site/{siteId}/scaffold/{scaffId}/detailsscaffold";

    }


    @GetMapping("/{inspectionId}/delete")
    public String showInspectionToDelete(@AuthenticationPrincipal CurrentUser customUser,
                                         Model model,
                                         @PathVariable("siteId") Long siteId,
                                         @PathVariable("scaffId") Long scaffId,
                                         @PathVariable("inspectionId") Long inspectionId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return "admin/403";
        }

        return "app/inspection/deleteinspection";
    }

    @GetMapping("/{inspectionId}/delete/confirm")
    public RedirectView deleteInspection(@AuthenticationPrincipal CurrentUser customUser,
                                         @PathVariable("siteId") Long siteId,
                                         @PathVariable("scaffId") Long scaffId,
                                         @PathVariable("inspectionId") Long inspectionId) {

        User entityUser = customUser.getUser();
        User myUser = userService.findByUserName(entityUser.getUsername());

        if (!securityService.hasAccess(myUser.getId(), siteId)) {
            return new RedirectView("/404");
        }

        Inspection inspectionToRemove = inspectionRepository.getById(inspectionId);
        Scaffold chosenScaffold = scaffoldRepository.getById(scaffId);


        siteRepository.getById(siteId).getInspectionList().remove(inspectionToRemove);
        inspectionRepository.delete(inspectionToRemove);

        Inspection lastInspection = inspectionRepository.getFirstByScaffoldId(chosenScaffold.getId());

        if (chosenScaffold.getInspectionsList().isEmpty()) {
            chosenScaffold.setApproval(false);
        } else {
            if (lastInspection.getDateOfInspection().isAfter(LocalDate.now().minusDays(7)) && inspectionToRemove.isApproved() == true) {
                chosenScaffold.setApproval(true);
            } else {
                chosenScaffold.setApproval(false);
            }
        }

        scaffoldRepository.save(chosenScaffold);
        return new RedirectView("/app/site/{siteId}/scaffold/{scaffId}/detailsscaffold");

    }

    @GetMapping("/{inspectionId}/edit")
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

    @PostMapping("/{inspectionId}/edit")
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
            return "app/scaffold/editscaffold";
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


        if (myInspection.getDateOfInspection().isAfter(LocalDate.now().minusDays(7)) && myInspection.isApproved() == true) {
            chosenScaffold.setApproval(true);
        } else {
            chosenScaffold.setApproval(false);
        }


        scaffoldRepository.save(chosenScaffold);
        siteRepository.getById(siteId).getInspectionList().add(myInspection);


        return "redirect:/app/site/{siteId}/scaffold/{scaffId}/detailsscaffold";


    }


    // if grade of inspector is lower than the scaffolding, do not let managing inspections
}
