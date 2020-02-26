package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vegd.service.NewsService;

import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    NewsService newsService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showNews(Model model) throws SQLException {
        model.addAttribute("news", newsService.getAll());
        return "news/allNews";
    }

}

