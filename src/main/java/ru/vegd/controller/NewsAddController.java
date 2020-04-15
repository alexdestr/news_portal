package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.News;
import ru.vegd.entity.Tag;
import ru.vegd.service.NewsService;
import ru.vegd.service.TagService;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ru.vegd.controller.PathConstants.*;

@Controller
public class NewsAddController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @GetMapping(value = "news/create")
    public String doGet(HttpServletRequest request, Model model) {
        model.addAttribute("news", new News());
        return PATH_NEWS_CREATE;
    }

    @PostMapping(value = "news/addNews")
    public String doPost(HttpServletRequest request, @ModelAttribute("news") News news) {

        Tag tag = new Tag();

        String tags;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String author = auth.getName();

        tags = request.getParameter("tags");

        try {
            news.setAuthorId(userService.getUserIdByLogin(author));
            Long id = newsService.add(news);
            tag.setNewsID(id);

            for (String str : tags.split(",")) {
                tag.setTags(str.trim());
                tagService.add(tag);
            }

        } catch (Exception e) {
            /*return ERROR;*/
        }

        return REDIRECT;
    }

}
