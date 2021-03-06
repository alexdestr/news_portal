package ru.vegd.dao;

import ru.vegd.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsDAO {

    List getAll() throws SQLException;
    List getPaginatedNews(Long beginIndex, Long endIndex) throws SQLException;
    List getPaginatedNewsBySearch(Long beginIndex, Long endIndex, String searchText) throws SQLException;
    Long getCountNewsByTitleSearch(String searchText) throws SQLException;
    List getPaginatedNewsByAuthor(Long beginIndex, Long endIndex, String searchText) throws SQLException;
    Long getCountNewsByAuthorSearch(String searchText) throws SQLException;
    List getPaginatedNewsByTags(Long beginIndex, Long endIndex, String searchText) throws SQLException;
    Long getCountNewsByTagsSearch(String searchText) throws SQLException;
    Long getNumberNews() throws SQLException;
    Long add(News news) throws SQLException;
    News read(Long ID) throws  SQLException;
    void delete(Long ID) throws SQLException;
    void update(News news) throws SQLException;

}
