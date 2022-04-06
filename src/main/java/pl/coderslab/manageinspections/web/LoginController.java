package pl.coderslab.manageinspections.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "/admin/login";
    }
    @GetMapping("/app")
    public String viewIndex() {
        return "/app/index"; }

}
