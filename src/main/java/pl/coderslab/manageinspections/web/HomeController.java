package pl.coderslab.manageinspections.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.manageinspections.service.CurrentUser;
import pl.coderslab.manageinspections.model.User;

@Controller
@RequestMapping("/app")
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String home() { return "home"; }
    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }

    @GetMapping("/")
    public String app() { return "app/index"; }

}