package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/user")
    public String user(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        try {
            model.addAttribute("userInfo", userService.read(userService.getUserIdByLogin(currentUser)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PathConstants.PATH_USER_PANEL;
    }

    @GetMapping("user/change")
    public String userChangeData() {
        return PathConstants.PATH_CHANGE_USER_DATA;
    }

    @PostMapping("user/changeData")
    public String userChangeMainData(Model model, HttpServletRequest request) {
        String newName = request.getParameter("name");
        String newLastName = request.getParameter("lastName");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        User user = new User();
        user.setUser_name(newName);
        user.setUser_last_name(newLastName);

        try {
            Long userId = userService.getUserIdByLogin(currentUser);
            user.setUser_id(userId);
            userService.updateData(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return PathConstants.PATH_CHANGE_USER_DATA;
    }

    @PostMapping("user/changePassword")
    public String userChangePassword(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        try {
            Long userId = userService.getUserIdByLogin(currentUser);
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String repeatPassword = request.getParameter("repeatPassword");

            if (newPassword.equals(repeatPassword) && passwordEncoder.matches(currentPassword, userService.read(userId).getHash_password())) {
                currentPassword = passwordEncoder.encode(currentPassword);
                newPassword = passwordEncoder.encode(newPassword);
                User user = new User();
                user.setUser_id(userId);
                user.setHash_password(newPassword);
                user.setUser_id(userId);
                userService.updatePassword(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return PathConstants.PATH_CHANGE_USER_DATA;
    }

    @PostMapping("user/deleteAccount")
    public String userDeleteAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        try {
            Long userId = userService.getUserIdByLogin(currentUser);
            userService.deactivateAccount(userId, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PathConstants.PATH_CHANGE_USER_DATA;
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
