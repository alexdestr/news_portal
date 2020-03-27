package ru.vegd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserDAOImpl.class.getName());

    @Autowired
    DataSource dataSource;

    private static final String SQL_GETALL = "SELECT * FROM public.users";
    private static final String SQL_GET_AUTHOR_NAMES = "SELECT login FROM public.users u JOIN public.news n ON u.user_id = n.author_id";
    private static final String SQL_GET_USER_ID_BY_LOGIN = "SELECT user_id FROM public.users WHERE login = ?";
    private static final String SQL_ADD = "INSERT INTO public.users (login, hash_password, user_name, user_last_name, registration_date, role_id) VALUES (?, ?, ?, ?, ?, ? )";
    private static final String SQL_READ = "SELECT * FROM public.users WHERE user_id = ?";
    private static final String SQL_DELETE = "DELETE FROM public.users WHERE user_id = ?";
    private static final String SQL_UPDATE = "UPDATE public.users SET login = ?, hash_password = ?, user_name = ?, user_last_name = ?, role_id = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_DATA = "UPDATE public.users SET user_name = ?, user_last_name = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_PASSWORD = "UPDATE public.users SET hash_password = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_ROLE = "UPDATE public.users SET role_id = ? WHERE user_id = ?";
    private static final String SQL_DEACTIVATE_ACCOUNT = "UPDATE public.users SET enabled = ? WHERE user_id = ?";

    @Override
    public List getAll() throws SQLException {

        Connection connection = dataSource.getConnection();

        List<User> userList = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        try {
          preparedStatement = connection.prepareStatement(SQL_GETALL);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setUser_id(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setHash_password(resultSet.getString("hash_password"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_last_name(resultSet.getString("user_last_name"));
                user.setDate_of_registration(resultSet.getTimestamp("registration_date"));
                user.setRole(Role.getRoleByID(resultSet.getInt("role_id")));

                userList.add(user);
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

        return userList;
    }

    @Override
    public List getAuthorNames() throws SQLException {

        Connection connection = dataSource.getConnection();

        List<User> authorList = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_GET_AUTHOR_NAMES);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setLogin(resultSet.getString("login"));

                authorList.add(user);
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

        return authorList;
    }

    @Override
    public Long getUserIdByLogin(String login) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        Long userId = -1L;

        try {
            preparedStatement = connection.prepareStatement(SQL_GET_USER_ID_BY_LOGIN);

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getLong("user_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        return userId;
    }

    @Override
    public Long add(User user) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        Long lastInsertedUserID = -1L;

        try {
            preparedStatement = connection.prepareStatement(SQL_ADD, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHash_password());
            preparedStatement.setString(3, user.getUser_name());
            preparedStatement.setString(4, user.getUser_last_name());
            preparedStatement.setTimestamp(5, user.getDate_of_registration());
            preparedStatement.setInt(6, user.getRole().getRoleID());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                lastInsertedUserID = resultSet.getLong(1);
            }
            logger.info("Success adding");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.warn("Request eror");
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (!connection.isClosed()) {
                connection.close();
            }
        }
        return lastInsertedUserID;
    }

    @Override
    public User read(Long ID) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(SQL_READ);
            preparedStatement.setLong(1, ID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setUser_id(resultSet.getLong("user_id"));
                user.setLogin(resultSet.getString("login"));
                user.setHash_password(resultSet.getString("hash_password"));
                user.setUser_name(resultSet.getString("user_name"));
                user.setUser_last_name(resultSet.getString("user_last_name"));
                user.setDate_of_registration(resultSet.getTimestamp("registration_date"));
                user.setRole(Role.getRoleByID(resultSet.getInt("role_id")));
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
        return user;
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
    public void update(User user) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getHash_password());
            preparedStatement.setString(3, user.getUser_name());
            preparedStatement.setString(4, user.getUser_last_name());
            preparedStatement.setInt(5, user.getRole().getRoleID());
            preparedStatement.setLong(6, user.getUser_id());

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

    @Override
    public void updateData(User user) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_DATA);

            preparedStatement.setString(1, user.getUser_name());
            preparedStatement.setString(2, user.getUser_last_name());
            preparedStatement.setLong(3, user.getUser_id());

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

    @Override
    public void updatePassword(User user) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_PASSWORD);

            preparedStatement.setString(1, user.getHash_password());
            preparedStatement.setLong(2, user.getUser_id());

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

    @Override
    public void updateRole(Long ID, Integer roleId) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE);

            preparedStatement.setInt(1, roleId);
            preparedStatement.setLong(2, ID);

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

    @Override
    public void deactivateAccount(Long ID, Boolean status) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DEACTIVATE_ACCOUNT);

            preparedStatement.setBoolean(1, status);
            preparedStatement.setLong(2, ID);

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
