package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vegd.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static ru.vegd.controller.PathConstants.PATH_SEARCH;

@Controller
public class SearchController {

    @Autowired
    private NewsService newsService;

    @Autowired
    Paginator paginator;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchNews(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Long id, @RequestParam(value = "searchText") String searchText) {
        Long numberNewsOnPage = 10L;
        Long maxNews;
        Long numPages;
        String searchType = "searchByTitle";

        if (request.getSession().getAttribute("searchType") != null) {
            searchType = (String) request.getSession().getAttribute("searchType");
        }
        if (request.getSession().getAttribute("numberNewsOnPage") != null) {
            numberNewsOnPage = Long.parseLong((String) request.getSession().getAttribute("numberNewsOnPage"));
        }

        paginator.configure(
                id,
                numberNewsOnPage,
                searchText,
                searchType
        );

        try {
            model.addAttribute("page", id);
            model.addAttribute("prevList", paginator.getPrevPages());
            model.addAttribute("nextList", paginator.getNextPages());
            model.addAttribute("searchText", searchText);

            if (searchType.equals("searchByTitle")) {
                model.addAttribute("news", newsService.getPaginatedNewsBySearch(id, numberNewsOnPage, searchText));
            }
            if (searchType.equals("searchByAuthor")) {
                model.addAttribute("news", newsService.getPaginatedNewsByAuthor(id, numberNewsOnPage, searchText));
            }
            if (searchType.equals("searchByTags")) {
                model.addAttribute("news", newsService.getPaginatedNewsByTags(id, numberNewsOnPage, searchText));
            }

        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_SEARCH;
    }
}
