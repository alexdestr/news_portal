package ru.vegd.dao.impl;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import DBUnitConfig.DBUnitConfig;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.User;
import ru.vegd.service.UserService;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/springTest.xml")
public class UserDAOImplTest extends DBUnitConfig{

    private long id = 46L;

    @Autowired
    UserService userService;

    User user;

    @Before
    public void before() {
        user = new User();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("resources/user-data.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public UserDAOImplTest() {
        super("123");
    }

    @Test
    public void getAll() throws Exception {

        List<User> persons = userService.getAll();

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("resources/user-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedData.getTable("Users").getRowCount(),persons.size());

    }

    @Test
    public void add() throws SQLException {
        user.setUser_id(id);
        user.setLogin("VincentVega");
        user.setHash_password("hashpass");
        user.setUser_name("Alex");
        user.setUser_last_name("Vega");
        userService.add(user);
        Assert.assertEquals(user.getLogin(), userService.read(id).getLogin());
        Assert.assertEquals(user.getHash_password(), userService.read(id).getHash_password());
        Assert.assertEquals(user.getUser_name(), userService.read(id).getUser_name());
        Assert.assertEquals(user.getUser_last_name(), userService.read(id).getUser_last_name());

        userService.delete(id);
    }

    @Test
    public void read() {



    }

    @Test
    public void delete() throws SQLException {
        user.setUser_id(id);
        user.setLogin("VincentVega");
        user.setHash_password("hashpass");
        user.setUser_name("Alex");
        user.setUser_last_name("Vega");
        userService.add(user);
        Assert.assertEquals(user.getLogin(), userService.read(id).getLogin());
        Assert.assertEquals(user.getHash_password(), userService.read(id).getHash_password());
        Assert.assertEquals(user.getUser_name(), userService.read(id).getUser_name());
        Assert.assertEquals(user.getUser_last_name(), userService.read(id).getUser_last_name());
        userService.delete(id);
        Assert.assertNotNull(userService.read(id));
    }

    @Test
    public void update() {
    }
}