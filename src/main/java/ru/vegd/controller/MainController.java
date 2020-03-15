package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vegd.service.NewsService;

import static ru.vegd.controller.PathConstants.ERROR;
import static ru.vegd.controller.PathConstants.PATH_MAIN;

@Controller
public class MainController {

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showNews(Model model) {
        try {
            model.addAttribute("news", newsService.getAll());
        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_MAIN;
    }

    @GetMapping(value = "/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/error";
    }

}

