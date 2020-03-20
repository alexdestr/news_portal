package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.LOGIN_PAGE;
import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class UserAddController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/addUser")
    public String doPost(HttpServletRequest httpServletRequest) {

        User user = new User();
        user.setLogin(httpServletRequest.getParameter("login"));
        user.setUser_name(httpServletRequest.getParameter("name"));
        user.setUser_last_name(httpServletRequest.getParameter("last_name"));
        user.setHash_password(passwordEncoder.encode(httpServletRequest.getParameter("password")));
        user.setRole(Role.ROLE_USER);

        try {
            userService.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return REDIRECT + "login";
    }

}
