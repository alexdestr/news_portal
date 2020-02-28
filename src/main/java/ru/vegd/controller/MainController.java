package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vegd.service.NewsService;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

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
            return ERROR;
        }
        return PATH_MAIN;
    }

}

