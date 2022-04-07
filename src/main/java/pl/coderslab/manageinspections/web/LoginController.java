package pl.coderslab.manageinspections.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.manageinspections.model.User;
import pl.coderslab.manageinspections.repository.UserRepository;

@Controller
@RequestMapping("/")
public class LoginController {

    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "/admin/login";
    }
    @GetMapping("/app")
    public String viewIndex() {
        return "/app/index"; }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        userRepository.save(user);
        return "dodano";
    }


}
