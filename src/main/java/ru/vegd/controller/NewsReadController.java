package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vegd.service.CommentService;
import ru.vegd.service.NewsService;

import java.sql.SQLException;

@Controller
public class NewsReadController {

    @Autowired
    NewsService newsService;

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/news/{id}")
    public String singleNews(@PathVariable Long id, Model model) throws SQLException {
        model.addAttribute("news", newsService.read(id));
        return "news/readOne";
    }

}
