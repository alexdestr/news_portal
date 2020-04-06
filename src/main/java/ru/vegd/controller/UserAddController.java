package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class UserAddController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public String addUser(HttpServletRequest httpServletRequest) {

        User user = new User();
        user.setLogin(httpServletRequest.getParameter("login"));
        user.setUserName(httpServletRequest.getParameter("name"));
        user.setUserLastName(httpServletRequest.getParameter("last_name"));
        user.setHashPassword(passwordEncoder.encode(httpServletRequest.getParameter("password")));
        user.setRole(Role.ROLE_USER);

        try {
            userService.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return REDIRECT + "login";
    }

}
