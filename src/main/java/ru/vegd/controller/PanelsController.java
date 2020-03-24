package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class PanelsController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String user(Model model) {
        return PathConstants.PATH_USER_PANEL;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        try {
            model.addAttribute("users", userService.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PathConstants.PATH_ADMIN_PANEL;
    }

    @PostMapping("/actionEditStatus")
    public String adminActionEditStatus(HttpServletRequest request) {

        User user1 = new User();
        user1.setLogin(request.getParameter("userSelect1"));
        String action1 = request.getParameter("action1");

        if (action1.equals("Ban")) {
            try {
                userService.updateRole(userService.getUserIdByLogin(user1.getLogin()), Role.ROLE_BANNED.getRoleID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (action1.equals("Unban")) {
            try {
                userService.updateRole(userService.getUserIdByLogin(user1.getLogin()), Role.ROLE_USER.getRoleID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return PathConstants.REDIRECT + "admin";
    }

    @PostMapping("/actionEditRole")
    public String adminActionEditRole(HttpServletRequest request) {
        User user2 = new User();
        user2.setLogin(request.getParameter("userSelect2"));
        user2.setRole(Role.valueOf("ROLE_" + request.getParameter("newRole")));

        try {
            userService.updateRole(userService.getUserIdByLogin(user2.getLogin()), user2.getRole().getRoleID());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return PathConstants.REDIRECT + "admin";
    }

}
