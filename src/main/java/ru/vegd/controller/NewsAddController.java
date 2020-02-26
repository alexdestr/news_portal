package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.service.NewsService;

import java.sql.SQLException;

@Controller
public class NewsAddController {

    @Autowired
    NewsService newsService;

    @PostMapping(value = "news/add")
    public String doPost(Model model) throws SQLException {

        return "news/allNews";
    }

}
