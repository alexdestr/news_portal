package ru.vegd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vegd.entity.User;

@Controller
public class LoginController {

    @GetMapping(value = "/login")
    public String signup() {
        return "login/login";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "login/registration";
    }

}
