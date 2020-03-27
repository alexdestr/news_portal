package ru.vegd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;
import ru.vegd.service.UserService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;


    @Override
    public List<User> getAll() throws SQLException {
        return userDAO.getAll();
    }

    @Override
    public List getAuthorNames() throws SQLException {
        return userDAO.getAuthorNames();
    }

    @Override
    public Long getUserIdByLogin(String login) throws SQLException {
        return userDAO.getUserIdByLogin(login);
    }

    @Override
    public Long add(User user) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setDate_of_registration(timestamp);
        return userDAO.add(user);
    }

    @Override
    public User read(Long ID) throws SQLException {
        return userDAO.read(ID);
    }

    @Override
    public void delete(Long ID) throws SQLException {
        userDAO.delete(ID);
    }

    @Override
    public void update(User user) throws SQLException {
        userDAO.update(user);
    }

    @Override
    public void updateData(User user) throws SQLException {
        userDAO.updateData(user);
    }

    @Override
    public void updatePassword(User user) throws SQLException {
        userDAO.updatePassword(user);
    }

    @Override
    public void updateRole(Long ID, Integer roleId) throws SQLException {
        userDAO.updateRole(ID, roleId);
    }

    @Override
    public void deactivateAccount(Long ID, Boolean status) throws SQLException {
        userDAO.deactivateAccount(ID, status);
    }
}
