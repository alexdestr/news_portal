package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vegd.entity.User;
import ru.vegd.service.NewsService;
import ru.vegd.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ru.vegd.controller.PathConstants.PATH_MAIN;

@Controller
public class MainController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showNews(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Long ID) {
        Long numberNewsOnPage = 10L;
        Long maxNews;
        Long numPages;

        try {
            if (request.getSession().getAttribute("numberNewsOnPage") != null) {
                numberNewsOnPage = Long.parseLong((String) request.getSession().getAttribute("numberNewsOnPage"));
            }
            maxNews = newsService.getNumberNews();
            if (maxNews % numberNewsOnPage > 0) {
                numPages = maxNews / numberNewsOnPage + 1;
            } else {
                numPages = maxNews / numberNewsOnPage;
            }

            List<Long> prevList = new ArrayList<>();
            List<Long> nextList = new ArrayList<>();
            for (Long i = ID - 1, z = ID - 4L; z <= i; z++) {
                if (z > 0 && z != ID) {
                    prevList.add(z);
                }
            }
            for (Long i = ID, z = ID + 4L; i <= z; i++) {
                if (i > 0 && i != ID && i <= numPages) {
                    nextList.add(i);
                }
            }

            model.addAttribute("page", ID);
            model.addAttribute("prevList", prevList);
            model.addAttribute("nextList", nextList);
            model.addAttribute("news", newsService.getPaginatedNews(ID, numberNewsOnPage));
        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_MAIN;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showSearchedNews(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Long ID, @RequestParam(value = "searchText", required = false) {

        return PATH_MAIN;
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/error";
    }

}

