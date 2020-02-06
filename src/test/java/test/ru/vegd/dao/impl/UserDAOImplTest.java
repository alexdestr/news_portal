package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.User;
import test.ru.vegd.TestConfig;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*TODO: Repair @Transactional*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
/*@Transactional*/
public class UserDAOImplTest{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DataSource dataSource;

    User user;

    @Before
    public void before() throws SQLException, DatabaseUnitException {
        user = new User();
    }

    @Test
    @DatabaseSetup(value = "/user-data.xml")
    public void getAll() throws Exception {

        System.out.println(userDAO.getAll());

        /*User user1 = new User();
        User user2 = new User();

        user1.setLogin("Zaza");
        user2.setLogin("Alex");
        user1.setHash_password("smthHash1");
        user2.setHash_password("smthHash2");
        user1.setUser_name("Jaja");
        user2.setUser_name("Alexey");
        user1.setUser_last_name("Kril");
        user2.setUser_last_name("Barchuk");
        user1.setDate_of_registration(Timestamp.valueOf("2019-11-19 15:03:56.52"));
        user2.setDate_of_registration(Timestamp.valueOf("2019-01-07 19:10:56.00"));

        List<User> expectedPersons = new ArrayList<>();
        expectedPersons.add(user1);
        expectedPersons.add(user2);

        System.out.println(userDAO.getAll());
        List<User> persons = userDAO.getAll();*/

        /*Assert.assertEquals(expectedPersons.get(0).getLogin(), persons.get(0).getLogin());
        Assert.assertEquals(expectedPersons.get(1).getLogin(), persons.get(1).getLogin());
        Assert.assertEquals(expectedPersons.get(0).getHash_password(), persons.get(0).getHash_password());
        Assert.assertEquals(expectedPersons.get(1).getHash_password(), persons.get(1).getHash_password());
        Assert.assertEquals(expectedPersons.get(0).getUser_name(), persons.get(0).getUser_name());
        Assert.assertEquals(expectedPersons.get(1).getUser_name(), persons.get(1).getUser_name());
        Assert.assertEquals(expectedPersons.get(0).getUser_last_name(), persons.get(0).getUser_last_name());
        Assert.assertEquals(expectedPersons.get(1).getUser_last_name(), persons.get(1).getUser_last_name());
        Assert.assertEquals(expectedPersons.get(0).getDate_of_registration(), persons.get(0).getDate_of_registration());
        Assert.assertEquals(expectedPersons.get(1).getDate_of_registration(), persons.get(1).getDate_of_registration());*/

    }

    @Test
    @Rollback()
    public void add() throws SQLException {

        user.setLogin("AlexandrOchka1999");
        user.setUser_name("A?");
        user.setUser_last_name("Sasha");
        user.setHash_password("hashPass1");
        user.setDate_of_registration(Timestamp.valueOf("2019-05-05 16:10:56.00"));
        userDAO.add(user);

        User expectedUser;
        Long deleteId;
        expectedUser = (User) userDAO.getAll().get(2);
        deleteId = expectedUser.getUser_id();
        expectedUser.setUser_id(null);

        Assert.assertEquals(user, expectedUser);
        userDAO.delete(deleteId);

    }

    @Test
    @DatabaseSetup(value = "/user-data.xml")
    public void read() {



    }

    @Test
    public void delete() throws SQLException {

        Integer size = userDAO.getAll().size();
        Integer exceptedSize = size - 1;

        userDAO.delete(11L);

    }

    @Test
    public void update() {
    }

}
