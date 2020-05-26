package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vegd.service.NewsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Paginator {

    @Autowired
    private NewsService newsService;

    private List prevList;
    private List nextList;

    private Long id;
    private Long numberNewsOnPage;
    private Long maxNews;
    private Long numPages;

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
        for (Long i = id - 1, z = id - 4L; z <= i; z++) {
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
        for (Long i = id, z = id + 4L; i <= z; i++) {
            if (i > 0 && i != id && i <= numPages) {
                nextList.add(i);
            }
        }
        return nextList;
    }

    public void setNumberSearchedNews(String searchText, String searchType) {
        try {
            if (searchType.equals("searchByTitle")) {
                setMaxNews(newsService.getCountNewsByTitleSearch(searchText));
            }
            if (searchType.equals("searchByAuthor")) {
                setMaxNews(newsService.getCountNewsByAuthorSearch(searchText));
            }
            if (searchType.equals("searchByTags")) {
                setMaxNews(newsService.getCountNewsByTagsSearch(searchText));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void configure(Long id, Long numberNewsOnPage, String searchText, String searchType) {
        setId(id);
        setNumberNewsOnPage(numberNewsOnPage);
        if (searchText != null) {
            setNumberSearchedNews(searchText, searchType);
        }
        else {
            try {
                setMaxNews(newsService.getNumberNews());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
