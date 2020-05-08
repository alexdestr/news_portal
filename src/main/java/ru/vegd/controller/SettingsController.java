package ru.vegd.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String settingPage() {
        return PathConstants.PATH_SETTINGS;
    }

    @PostMapping("settings/set")
    public String setSettings(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("numberNewsOnPage", request.getParameter("numberNews"));
        request.getSession().setAttribute("searchType", request.getParameter("searchType"));
        return PathConstants.REDIRECT;
    }

}
