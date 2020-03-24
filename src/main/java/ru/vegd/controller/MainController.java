package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vegd.service.NewsService;
import ru.vegd.service.UserService;

import static ru.vegd.controller.PathConstants.ERROR;
import static ru.vegd.controller.PathConstants.PATH_MAIN;

@Controller
public class MainController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showNews(Model model) {
        try {
            model.addAttribute("news", newsService.getAll());
            model.addAttribute("authors", userService.getAuthorNames());
        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_MAIN;
    }

    @GetMapping(value = "/login")
    public String signup() {
        return "login/login";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "login/registration";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/error";
    }

}

