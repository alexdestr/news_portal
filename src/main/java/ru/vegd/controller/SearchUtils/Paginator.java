package ru.vegd.controller.SearchUtils;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vegd.service.NewsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Paginator {

    private NewsService newsService;

    private List prevList;
    private List nextList;

    private Long id;
    private Long numberNewsOnPage;
    private Long maxNews;
    private Long numPages;

    private static final Long newsRange = 4L;

    public Paginator(NewsService newsService) {
        this.newsService = newsService;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberNewsOnPage(Long numberNewsOnPage) {
        this.numberNewsOnPage = numberNewsOnPage;
    }

    public void setMaxNews(Long maxNews) {
        this.maxNews = maxNews;
    }

    public List getPrevPages() {
        prevList = new ArrayList();
        if (maxNews % numberNewsOnPage > 0) {
            numPages = maxNews / numberNewsOnPage + 1;
        } else {
            numPages = maxNews / numberNewsOnPage;
        }
        for (Long i = id - 1, z = id - newsRange; z <= i; z++) {
            if (z > 0 && z != id) {
                prevList.add(z);
            }
        }
        return prevList;
    }

    public List getNextPages() {
        nextList = new ArrayList();
        if (maxNews % numberNewsOnPage > 0) {
            numPages = maxNews / numberNewsOnPage + 1;
        } else {
            numPages = maxNews / numberNewsOnPage;
        }
        for (Long i = id, z = id + newsRange; i <= z; i++) {
            if (i > 0 && i != id && i <= numPages) {
                nextList.add(i);
            }
        }
        return nextList;
    }

    public void setNumberSearchedNews(String searchText, SearchType searchType) {
        try {
            if (SearchType.SEARCH_BY_TITLE.equals(searchType)) {
                setMaxNews(newsService.getCountNewsByTitleSearch(searchText));
            }
            if (SearchType.SEARCH_BY_AUTHOR.equals(searchType)) {
                setMaxNews(newsService.getCountNewsByAuthorSearch(searchText));
            }
            if (SearchType.SEARCH_BY_TAGS.equals(searchType)) {
                setMaxNews(newsService.getCountNewsByTagsSearch(searchText));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void configure(Long id, Long numberNewsOnPage, String searchText, SearchType searchType) {
        setId(id);
        setNumberNewsOnPage(numberNewsOnPage);
        if (searchText != null) {
            setNumberSearchedNews(searchText, searchType);
        } else {
            try {
                setMaxNews(newsService.getNumberNews());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
