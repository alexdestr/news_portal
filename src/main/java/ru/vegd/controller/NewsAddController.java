package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.config.SecurityConfig;
import ru.vegd.entity.News;
import ru.vegd.entity.Tag;
import ru.vegd.service.NewsService;
import ru.vegd.service.TagService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.*;

@Controller
public class NewsAddController {

    @Autowired
    NewsService newsService;

    @Autowired
    TagService tagService;

    @GetMapping(value = "news/create")
    public String doGet(HttpServletRequest request) {
        return PATH_NEWS_CREATE;
    }

    @PostMapping(value = "news/addNews")
    public String doPost(HttpServletRequest request) {

        News news = new News();
        Tag tag = new Tag();

        String tags;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String author = auth.getName();

        news.setAuthor_id(2L);
        news.setTittle(request.getParameter("title"));
        news.setNews_text(request.getParameter("text"));
        tags = request.getParameter("tags");

        try {
            Long id = newsService.add(news);
            tag.setNews_ID(id);

            for (String str : tags.split(", ")) {
                tag.setTags(str);
                tagService.add(tag);
            }

        } catch (Exception e) {
            /*return ERROR;*/
        }

        return REDIRECT;
    }

}
