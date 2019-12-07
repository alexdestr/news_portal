package ru.vegd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vegd.dao.UserDAO;
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
    public void add(User user) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setDate_of_registration(timestamp);
        userDAO.add(user);
    }

    @Override
    public User read(long ID) throws SQLException {
        return userDAO.read(ID);
    }

    @Override
    public void delete(long ID) throws SQLException {
        userDAO.delete(ID);
    }

    @Override
    public void update(User user) throws SQLException {
        userDAO.update(user);
    }
}
