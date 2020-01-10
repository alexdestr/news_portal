package ru.vegd.dao;

import ru.vegd.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsDAO {

    List getAll() throws SQLException;
    void add(News aNews) throws SQLException;
    News read(long ID) throws  SQLException;
    void delete(long ID) throws SQLException;
    void update(News aNews) throws SQLException;

}
