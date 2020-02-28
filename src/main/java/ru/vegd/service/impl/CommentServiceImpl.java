package ru.vegd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vegd.dao.CommentDAO;
import ru.vegd.entity.Comment;
import ru.vegd.service.CommentService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Override
    public List getAll() throws SQLException {
        return commentDAO.getAll();
    }

    @Override
    public void add(Comment comment) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setSending_date(timestamp);
        commentDAO.add(comment);
    }

    @Override
    public Comment read(Long ID) throws SQLException {
        return commentDAO.read(ID);
    }

    @Override
    public List readLinkedComments(Long ID) throws SQLException {
        return commentDAO.readLinkedComments(ID);
    }

    @Override
    public void delete(Long ID) throws SQLException {
        commentDAO.delete(ID);
    }

    @Override
    public void update(Comment comment) throws SQLException {
        commentDAO.update(comment);
    }
}
