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
    private CommentDAO commentDAO;

    @Override
    public List getAll() throws SQLException {
        return commentDAO.getAll();
    }

    @Override
    public void add(Comment comment) throws SQLException {
        if (comment.getAuthorName() != null &&
                comment.getCommentText() != null &&
                comment.getAuthorName() != null &&
                comment.getNewsId() != null &&
                comment.getCommentText().trim().length() > 1)
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            comment.setSendingDate(timestamp);
            commentDAO.add(comment);
        }
    }

    @Override
    public Comment read(Long ID) throws SQLException {
        if (ID != null && ID >= 0) {
            return commentDAO.read(ID);
        }
        return null;
    }

    @Override
    public List readLinkedComments(Long ID) throws SQLException {
        return commentDAO.readLinkedComments(ID);
    }

    @Override
    public void delete(Long ID) throws SQLException {
        if (ID != null && ID >= 0) {
            commentDAO.delete(ID);
        }
    }

    @Override
    public void deleteLinked(Long ID) throws SQLException {
        if (ID != null && ID >= 0) {
            commentDAO.deleteLinked(ID);
        }
    }

    @Override
    public void update(Comment comment) throws SQLException {
        if (comment.getAuthorName() != null &&
                comment.getCommentText() != null &&
                comment.getAuthorId() != null &&
                comment.getNewsId() != null &&
                comment.getCommentText().trim().length() > 1 &&
                comment.getCommentId() != null)
        {
            commentDAO.update(comment);
        }
    }
}
