package ru.vegd.service;

import ru.vegd.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List getAll() throws SQLException;
    Long add(User user) throws SQLException;
    User read(Long ID) throws SQLException;
    void delete(Long ID) throws SQLException;
    void update(User user) throws SQLException;

}
