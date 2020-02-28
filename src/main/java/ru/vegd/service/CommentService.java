package ru.vegd.service;

import ru.vegd.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {

    List getAll() throws SQLException;
    void add(Comment comment) throws SQLException;
    Comment read(Long ID) throws SQLException;
    List readLinkedComments(Long ID) throws SQLException;
    void delete(Long ID) throws SQLException;
    void update(Comment comment) throws SQLException;

}
