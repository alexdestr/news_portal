package ru.vegd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vegd.dao.CommentDAO;
import ru.vegd.dao.NewsDAO;
import ru.vegd.dao.TagDAO;
import ru.vegd.entity.News;
import ru.vegd.service.NewsService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private TagDAO tagDAO;

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public List getAll() throws SQLException {
        return newsDAO.getAll();
    }

    @Override
    public Long add(News news) throws SQLException {
        if (news.getNewsText() != null &&
                news.getTitle() != null &&
                news.getAuthorName() != null &&
                news.getTitle().trim().length() >= 3 &&
                news.getNewsText().trim().length() >= 6) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            news.setPublicDate(timestamp);
            return newsDAO.add(news);
        }
        return null;
    }

    @Override
    public List getPaginatedNews(Long ID, Long numberNews) throws SQLException {
        Long beginIndex = (ID - 1) * numberNews;
        Long endIndex = ID * numberNews;

        if (beginIndex != 0) {
            beginIndex += 1;
        }

        return newsDAO.getPaginatedNews(beginIndex, endIndex);
    }

    @Override
    public List getPaginatedNewsBySearch(Long ID, Long numberNews, String searchText) throws SQLException {
        Long beginIndex = (ID - 1) * numberNews;
        Long endIndex = ID * numberNews;

        if (beginIndex != 0) {
            beginIndex += 1;
        }

        return newsDAO.getPaginatedNewsBySearch(beginIndex, endIndex, searchText);
    }

    @Override
    public Long getCountNewsByTitleSearch(String searchText) throws SQLException {
        return newsDAO.getCountNewsByTitleSearch(searchText);
    }

    @Override
    public List getPaginatedNewsByAuthor(Long ID, Long numberNews, String searchText) throws SQLException {
        Long beginIndex = (ID - 1) * numberNews;
        Long endIndex = ID * numberNews;

        if (beginIndex != 0) {
            beginIndex += 1;
        }

        return newsDAO.getPaginatedNewsByAuthor(beginIndex, endIndex, searchText);
    }

    @Override
    public Long getCountNewsByAuthorSearch(String searchText) throws SQLException {
        return newsDAO.getCountNewsByAuthorSearch(searchText);
    }

    @Override
    public List getPaginatedNewsByTags(Long ID, Long numberNews, String searchText) throws SQLException {
        Long beginIndex = (ID - 1) * numberNews;
        Long endIndex = ID * numberNews;

        if (beginIndex != 0) {
            beginIndex += 1;
        }

        return newsDAO.getPaginatedNewsByTags(beginIndex, endIndex, searchText);
    }

    @Override
    public Long getCountNewsByTagsSearch(String searchText) throws SQLException {
        return newsDAO.getCountNewsByTagsSearch(searchText);
    }

    @Override
    public Long getNumberNews() throws SQLException {
        return newsDAO.getNumberNews();
    }

    @Override
    public News read(Long ID) throws SQLException {
        if (ID != null && ID >= 0) {
            return newsDAO.read(ID);
        }
        return null;
    }

    @Override
    public void delete(Long ID) throws SQLException {
        if (ID != null && ID >= 0) {
            commentDAO.deleteLinked(ID);
            tagDAO.delete(ID);
            newsDAO.delete(ID);
        }
    }

    @Override
    public void update(News news) throws SQLException {
        if (news.getNewsText() != null &&
                news.getTitle() != null &&
                news.getAuthorName() != null &&
                news.getPublicDate() != null &&
                news.getTitle().trim().length() >= 3 &&
                news.getNewsText().trim().length() >= 6) {
            newsDAO.update(news);
        }
    }
}
