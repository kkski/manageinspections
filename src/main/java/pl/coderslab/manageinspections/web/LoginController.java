package pl.coderslab.manageinspections.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.UserRepository;
import pl.coderslab.manageinspections.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login() {
        return "/admin/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/admin/logout";
    }

    @GetMapping("/403")
    public String display403Error() {
        return "/admin/403";
    }
    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "admin/register";
    }
    @PostMapping("register")
    public String register(@ModelAttribute("userForm") User userForm, Model model) {
        userService.saveUser(userForm);
        return "/admin/login";
    }



    @GetMapping("/app")
    public String viewIndex() {
        return "/app/index"; }


    @GetMapping("/create-user") @ResponseBody public String createUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        userService.saveUser(user); return "user"; }


}
