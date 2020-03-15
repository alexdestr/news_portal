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
    NewsDAO newsDAO;

    @Autowired
    TagDAO tagDAO;

    @Autowired
    CommentDAO commentDAO;

    @Override
    public List getAll() throws SQLException {
        return newsDAO.getAll();
    }

    @Override
    public Long add(News aNews) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        aNews.setPublic_date(timestamp);
        return newsDAO.add(aNews);
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
