package ru.vegd.dao;

import ru.vegd.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagDAO {

    List getAll() throws SQLException;
    void add(Tag tag) throws SQLException;
    Tag read(Long ID) throws  SQLException;
    void delete(Long ID) throws SQLException;
    void update(Tag tag) throws SQLException;

}
