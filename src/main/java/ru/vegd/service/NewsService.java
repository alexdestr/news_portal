package ru.vegd.service;

import ru.vegd.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsService {

    List getAll() throws SQLException;
    Long add(News aNews) throws SQLException;
    List getPaginatedNews(Long ID, Long numberNews) throws SQLException;
    List getPaginatedNewsBySearch(Long ID, Long numberNews, String searchText) throws SQLException;
    Long getNumberNews() throws SQLException;
    News read(Long ID) throws SQLException;
    void delete(Long ID) throws SQLException;
    void update(News aNews) throws SQLException;

}
