package ru.vegd.service;

import ru.vegd.entity.Tag;

import java.sql.SQLException;
import java.util.List;

public interface TagService {

    List getAll() throws SQLException;
    void add(Tag tag) throws SQLException;
    List<Tag> read(Long ID) throws SQLException;
    void delete(Long ID) throws SQLException;
    void update(Tag tag) throws SQLException;

}
