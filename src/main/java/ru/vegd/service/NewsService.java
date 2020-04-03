package ru.vegd.service;

import ru.vegd.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {

    List getAll() throws SQLException;
    Long add(News aNews) throws SQLException;
    List getTenNews(Long ID) throws SQLException;
    News read(Long ID) throws SQLException;
    void delete(Long ID) throws SQLException;
    void update(News aNews) throws SQLException;

}
