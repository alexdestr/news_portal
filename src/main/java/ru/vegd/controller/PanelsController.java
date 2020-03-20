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

    /*TODO: MAKE BAN / UNBAN SYSTEM*/
    @PostMapping("/action")
    public String adminAction(HttpServletRequest request) {

        /*----------------------------BAN / UNBAN------------------------------*/

        User user1 = new User();
        user1.setLogin(request.getParameter("userSelect1"));
        String action = request.getParameter("action");

        if (action.equals("Ban")) {
            System.out.println(user1 + "was banned");
        }

        if (action.equals("Unban")) {
            System.out.println(user1 + "was unbanned");
        }

        /*---------------------------SET ROLE---------------------------------*/

        User user2 = new User();
        user2.setLogin(request.getParameter("userSelect2"));
        user2.setRole(Role.valueOf("ROLE_" + request.getParameter("newRole")));


        return PathConstants.REDIRECT;
    }

}
