package ru.vegd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vegd.dao.NewsDAO;
import ru.vegd.entity.News;
import ru.vegd.service.NewsService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDAO newsDAO;

    @Override
    public List getAll() throws SQLException {
        return newsDAO.getAll();
    }

    @Override
    public void add(News aNews) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        aNews.setPublic_date(timestamp);
        newsDAO.add(aNews);
    }

    @Override
    public News read(long ID) throws SQLException {
        return newsDAO.read(ID);
    }

    @Override
    public void delete(long ID) throws SQLException {
        newsDAO.delete(ID);
    }

    @Override
    public void update(News aNews) throws SQLException {
        newsDAO.update(aNews);
    }
}
