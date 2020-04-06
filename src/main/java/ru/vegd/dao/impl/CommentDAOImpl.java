package ru.vegd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vegd.dao.CommentDAO;
import ru.vegd.entity.Comment;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommentDAOImpl.class.getName());
    @Autowired
    private DataSource dataSource;


    private static final String SQL_GETALL = "SELECT * " +
            "FROM comments";
    private static final String SQL_ADD = "INSERT " +
            "INTO comments (news_id, author_id, comment_text, creation_date) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_READ = "SELECT * " +
            "FROM comments " +
            "WHERE comments_id = ?";
    private static final String SQL_READ_LINKED = "SELECT * " +
            "FROM comments " +
            "WHERE news_id = ?";
    private static final String SQL_DELETE = "DELETE " +
            "FROM comments " +
            "WHERE comments.\"comments_id\" = ?";
    private static final String SQL_DELETE_LINKED = "DELETE " +
            "FROM comments " +
            "WHERE comments.\"news_id\" = ?";
    private static final String SQL_UPDATE = "UPDATE comments " +
            "SET news_id = ?, author_id = ?, comment_text = ? " +
            "WHERE comments_id = ?";

    @Override
    public List getAll() throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL_GETALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();

                comment.setCommentId(resultSet.getLong("comments_id"));
                comment.setNewsId(resultSet.getLong("news_id"));
                comment.setAuthorId(resultSet.getLong("author_id"));
                comment.setCommentText(resultSet.getString("comment_text"));
                comment.setSendingDate(resultSet.getTimestamp("creation_date"));

                commentList.add(comment);
            }

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }

        return commentList;
    }

    @Override
    public void add(Comment comment) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_ADD);

            preparedStatement.setLong(1, comment.getNewsId());
            preparedStatement.setLong(2, comment.getAuthorId());
            preparedStatement.setString(3, comment.getCommentText());
            preparedStatement.setTimestamp(4, comment.getSendingDate());

            preparedStatement.executeUpdate();
            logger.info("Success adding");

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Comment read(Long ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        Comment comment = new Comment();
        try {
            preparedStatement = connection.prepareStatement(SQL_READ);
            preparedStatement.setLong(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                comment.setCommentId(resultSet.getLong("comments_id"));
                comment.setNewsId(resultSet.getLong("news_id"));
                comment.setAuthorId(resultSet.getLong("author_id"));
                comment.setCommentText(resultSet.getString("comment_text"));
                comment.setSendingDate(resultSet.getTimestamp("creation_date"));
            }

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        return comment;
    }

    @Override
    public List<Comment> readLinkedComments(Long ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        List<Comment> commentList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(SQL_READ_LINKED);
            preparedStatement.setLong(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();

                comment.setCommentId(resultSet.getLong("comments_id"));
                comment.setNewsId(resultSet.getLong("news_id"));
                comment.setAuthorId(resultSet.getLong("author_id"));
                comment.setCommentText(resultSet.getString("comment_text"));
                comment.setSendingDate(resultSet.getTimestamp("creation_date"));

                commentList.add(comment);
            }

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        return commentList;
    }

    @Override
    public void delete(Long ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, ID);
            preparedStatement.executeUpdate();
            logger.info("Success delete");

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteLinked(Long ID) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_LINKED);
            preparedStatement.setLong(1, ID);
            preparedStatement.executeUpdate();
            logger.info("Success delete");

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public void update(Comment comment) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setLong(1, comment.getNewsId());
            preparedStatement.setLong(2, comment.getAuthorId());
            preparedStatement.setString(3, comment.getCommentText());
            preparedStatement.setLong(4, comment.getCommentId());

            preparedStatement.executeUpdate();
            logger.info("Success update");

        } catch (SQLException e) {
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }
}
