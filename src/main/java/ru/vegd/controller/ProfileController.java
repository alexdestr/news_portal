package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping(value = "users/{login}")
    public String profile(@PathVariable String login, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("user", userService.read(userService.getUserIdByLogin(login)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PathConstants.PATH_SHOW_USER;
    }

}
