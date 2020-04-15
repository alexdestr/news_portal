package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vegd.service.CommentService;
import ru.vegd.service.NewsService;
import ru.vegd.service.TagService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.ERROR;
import static ru.vegd.controller.PathConstants.PATH_NEWS_READ;

@Controller
public class NewsReadController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TagService tagService;

    @GetMapping(value = "/news/{id}")
    public String singleNews(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("news", newsService.read(id));
            model.addAttribute("comments", commentService.readLinkedComments(id));
            model.addAttribute("tags", tagService.read(id));
        } catch (Exception e) {
            /*return ERROR;*/
        }
        request.getSession().setAttribute("newsID", id);
        return PATH_NEWS_READ;
    }

}
