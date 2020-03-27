package ru.vegd.dao;

import ru.vegd.entity.Role;
import ru.vegd.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List getAll() throws SQLException;
    List getAuthorNames() throws SQLException;
    Long getUserIdByLogin(String login) throws SQLException;
    Long add(User user) throws SQLException;
    User read(Long ID) throws  SQLException;
    void delete(Long ID) throws SQLException;
    void update(User user) throws SQLException;
    void updateData(User user) throws SQLException;
    void updatePassword(User user) throws SQLException;
    void updateRole(Long ID, Integer roleId) throws SQLException;
    void deactivateAccount(Long ID, Boolean status) throws SQLException;

}
