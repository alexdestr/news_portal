package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.News;
import ru.vegd.entity.Tag;
import ru.vegd.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.ERROR;
import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class NewsAddController {

    @Autowired
    NewsService newsService;

    @PostMapping(value = "news/addNews")
    public String doPost(HttpServletRequest request) {

        News news = new News();
        Tag tag = new Tag();

        news.setAuthor_id(2L);
        news.setTittle(request.getParameter("title"));
        news.setNews_text(request.getParameter("text"));

        try {
            newsService.add(news);
        } catch (Exception e) {
            /*return ERROR;*/
        }

        return REDIRECT;
    }

}
