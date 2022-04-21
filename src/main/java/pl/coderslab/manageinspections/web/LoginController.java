package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.coderslab.manageinspections.dtos.UserDto;
import pl.coderslab.manageinspections.model.Inspector;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.InspectorRepository;
import pl.coderslab.manageinspections.repository.UserRepository;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    private final UserRepository userRepository;
    private final InspectorRepository inspectorRepository;
    private final UserService userService;
    public LoginController(UserService userService, InspectorRepository inspectorRepository, UserRepository userRepository) {
        this.userService = userService;
        this.inspectorRepository = inspectorRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String index() {
        return "/index";
    }



    @GetMapping("/login")
    public String login() {
        return "/admin/login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "/admin/logout";
    }

    @GetMapping("/404")
    public String display403Error() {
        return "/admin/403";
    }
    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "admin/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userForm") UserDto userForm,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/register";
        }
        userService.saveUser(userForm);
        return "redirect:/";
    }




}
