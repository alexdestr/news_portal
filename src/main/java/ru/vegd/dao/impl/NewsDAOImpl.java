package ru.vegd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vegd.dao.NewsDAO;
import ru.vegd.entity.News;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewsDAOImpl.class.getName());

    @Autowired
    DataSource dataSource;


    private static final String SQL_GETALL = "SELECT * FROM \"News\"";
    private static final String SQL_ADD = "INSERT INTO \"News\" (author_id, tittle, news_text, creation_date) VALUES ( ?, ?, ?, ?)";
    private static final String SQL_READ = "SELECT * FROM \"News\" WHERE news_id = ?";
    private static final String SQL_DELETE = "DELETE FROM \"News\" WHERE \"News\".\"news_id\" = ?";
    private static final String SQL_UPDATE = "UPDATE \"News\" SET author_id = ?, tittle = ?, news_text = ? WHERE news_id = ?";

    @Override
    public List getAll() throws SQLException {

        List<News> newsList = new ArrayList<>();

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_GETALL);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                News aNews = new News();

                aNews.setNews_id(resultSet.getLong("news_id"));
                aNews.setAuthor_id(resultSet.getLong("author_id"));
                aNews.setTittle(resultSet.getString("tittle"));
                aNews.setNews_text(resultSet.getString("news_text"));
                aNews.setPublic_date(resultSet.getTimestamp("public_date"));

                newsList.add(aNews);
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

        return newsList;
    }

    @Override
    public void add(News aNews) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_ADD);

            preparedStatement.setLong(1, aNews.getAuthor_id());
            preparedStatement.setString(2, aNews.getTittle());
            preparedStatement.setString(3, aNews.getNews_text());
            preparedStatement.setTimestamp(4, aNews.getPublic_date());

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
    public News read(Long ID) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        News aNews = new News();

        try {
            preparedStatement = connection.prepareStatement(SQL_READ);
            preparedStatement.setLong(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                aNews.setNews_id(resultSet.getLong("news_id"));
                aNews.setAuthor_id(resultSet.getLong("author_id"));
                aNews.setTittle(resultSet.getString("tittle"));
                aNews.setNews_text(resultSet.getString("news_text"));
                aNews.setPublic_date(resultSet.getTimestamp("public_date"));
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
        return aNews;
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
    public void update(News aNews) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setLong(1, aNews.getAuthor_id());
            preparedStatement.setString(2, aNews.getTittle());
            preparedStatement.setString(3, aNews.getNews_text());
            preparedStatement.setLong(4, aNews.getNews_id());

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
