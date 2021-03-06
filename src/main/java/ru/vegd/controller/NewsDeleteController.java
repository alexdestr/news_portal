package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class NewsDeleteController {

    @Autowired
    private NewsService newsService;

    @PostMapping(value = "news/delete")
    public String deleteNews(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("news_id"));
            newsService.delete(id);
        } catch (SQLException e) {

        }
        return REDIRECT;
    }

}
