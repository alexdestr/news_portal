package ru.vegd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vegd.dao.NewDAO;
import ru.vegd.entity.New;
import ru.vegd.service.NewsService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Autowired
    NewDAO newDAO;

    @Override
    public List getAll() throws SQLException {
        return newDAO.getAll();
    }

    @Override
    public void add(New aNew) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        aNew.setPublic_date(timestamp);
        newDAO.add(aNew);
    }

    @Override
    public New read(long ID) throws SQLException {
        return newDAO.read(ID);
    }

    @Override
    public void delete(long ID) throws SQLException {
        newDAO.delete(ID);
    }

    @Override
    public void update(New aNew) throws SQLException {
        newDAO.update(aNew);
    }
}
