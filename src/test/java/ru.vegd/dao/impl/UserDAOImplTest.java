package ru.vegd.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/springTest.xml")
public class UserDAOImplTest{

    @Autowired
    UserDAO userDAO;

    @Autowired
    DataSource dataSource;

    User user;

    @Before
    public void before() {
        user = new User();
    }

    @Test
    public void getAll() throws Exception {

        List<User> persons = userDAO.getAll();

        Connection con = dataSource.getConnection();

    }

    @Test
    public void add() throws SQLException {

    }

    @Test
    public void read() {



    }

    @Test
    public void delete() throws SQLException {

    }

    @Test
    public void update() {
    }
}