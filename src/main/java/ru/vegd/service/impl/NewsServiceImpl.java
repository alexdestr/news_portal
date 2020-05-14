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
    public Long add(News aNews) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        aNews.setPublicDate(timestamp);
        return newsDAO.add(aNews);
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
    public List getPaginatedNewsByAuthor(Long ID, Long numberNews, String searchText) throws SQLException {
        Long beginIndex = (ID - 1) * numberNews;
        Long endIndex = ID * numberNews;

        if (beginIndex != 0) {
            beginIndex += 1;
        }

        return newsDAO.getPaginatedNewsByAuthor(beginIndex, endIndex, searchText);
    }

    @Override
    public Long getNumberNews() throws SQLException {
        return newsDAO.getNumberNews();
    }

    @Override
    public News read(Long ID) throws SQLException {
        return newsDAO.read(ID);
    }

    @Override
    public void delete(Long ID) throws SQLException {
        commentDAO.deleteLinked(ID);
        tagDAO.delete(ID);
        newsDAO.delete(ID);
    }

    @Override
    public void update(News aNews) throws SQLException {
        newsDAO.update(aNews);
    }
}
