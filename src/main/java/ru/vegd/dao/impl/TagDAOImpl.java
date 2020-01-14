package ru.vegd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vegd.dao.TagDAO;
import ru.vegd.entity.Tag;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(TagDAOImpl.class.getName());

    @Autowired
    DataSource dataSource;

    private static final String SQL_GETALL = "SELECT * FROM \"tags\"";
    private static final String SQL_ADD = "INSERT INTO \"tags\" (news_id, tags) VALUES ( ?, ?)";
    private static final String SQL_READ = "SELECT * FROM \"tags\" WHERE news_id = ?";
    private static final String SQL_DELETE = "DELETE FROM \"tags\" WHERE \"Tags\".\"news_id\" = ?";
    private static final String SQL_UPDATE = "UPDATE \"tags\" SET tags = ? WHERE news_id = ?";

    @Override
    public List getAll() throws SQLException {

        Connection connection = dataSource.getConnection();
        List<Tag> tagList = new ArrayList<>();


        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL_GETALL);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tag tag = new Tag();

                tag.setNews_ID(resultSet.getLong("news_ID"));
                tag.setTags(resultSet.getString("tag"));

                tagList.add(tag);
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
        return tagList;
    }

    @Override
    public void add(Tag tag) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_ADD);

            preparedStatement.setLong(1, tag.getNews_ID());
            preparedStatement.setString(2, tag.getTags());

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
    public Tag read(Long ID) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        Tag tag = new Tag();

        try {
            preparedStatement = connection.prepareStatement(SQL_READ);
            preparedStatement.setLong(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tag.setNews_ID(resultSet.getLong("news_id"));
                tag.setTags(resultSet.getString("tag"));
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
        return tag;
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
    public void update(Tag tag) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, tag.getTags());
            preparedStatement.setLong(2, tag.getNews_ID());

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
