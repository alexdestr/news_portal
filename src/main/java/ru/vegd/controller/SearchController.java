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

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchNews(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Long ID, @RequestParam(value = "searchText") String searchText) {
        Long numberNewsOnPage = 10L;
        Long maxNews;
        Long numPages;
        String searchType = "searchByTitle";
        if (request.getSession().getAttribute("searchType") != null) {
            searchType = (String) request.getSession().getAttribute("searchType");
        }

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
            model.addAttribute("searchText", searchText);

            if (searchType.equals("searchByTitle")) {
                model.addAttribute("news", newsService.getPaginatedNewsBySearch(ID, numberNewsOnPage, searchText));
            }
            if (searchType.equals("searchByAuthor")) {
                model.addAttribute("news", newsService.getPaginatedNewsByAuthor(ID, numberNewsOnPage, searchText));
            }

        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_SEARCH;
    }
}
