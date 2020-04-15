package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;
import ru.vegd.service.UserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class UserAddController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public String addUser(Model model, HttpServletRequest request, @ModelAttribute("user") User user) {

        user.setRole(Role.ROLE_USER);

        try {
            userService.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return REDIRECT + "login";
    }

}
