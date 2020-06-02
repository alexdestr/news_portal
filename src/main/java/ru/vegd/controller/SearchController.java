package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vegd.controller.SearchUtils.Paginator;
import ru.vegd.controller.SearchUtils.SearchType;
import ru.vegd.service.NewsService;

import javax.servlet.http.HttpServletRequest;

import static ru.vegd.controller.PathConstants.PATH_SEARCH;

@Controller
public class SearchController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchNews(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Long id, @RequestParam(value = "searchText") String searchText) {
        Paginator paginator = new Paginator(newsService);
        Long numberNewsOnPage = 10L;
        Long maxNews;
        Long numPages;
        SearchType searchType = SearchType.SEARCH_BY_TITLE;

        if (request.getSession().getAttribute("searchType") != null) {
            searchType = SearchType.valueOf((String) request.getSession().getAttribute("searchType"));
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

            if (SearchType.SEARCH_BY_TITLE.equals(searchType)) {
                model.addAttribute("news", newsService.getPaginatedNewsBySearch(id, numberNewsOnPage, searchText));
            }
            if (SearchType.SEARCH_BY_AUTHOR.equals(searchType)) {
                model.addAttribute("news", newsService.getPaginatedNewsByAuthor(id, numberNewsOnPage, searchText));
            }
            if (SearchType.SEARCH_BY_TAGS.equals(searchType)) {
                model.addAttribute("news", newsService.getPaginatedNewsByTags(id, numberNewsOnPage, searchText));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PATH_SEARCH;
    }
}
