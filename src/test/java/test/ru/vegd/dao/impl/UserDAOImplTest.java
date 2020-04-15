package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.Role;
import ru.vegd.entity.User;
import test.ru.vegd.util.SequenceReseter;
import test.ru.vegd.TestConfig;

import java.sql.SQLException;
import java.sql.Timestamp;

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
    private SequenceReseter seqReseter;

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void getAll() throws Exception {

        Integer expectedSize = 2;
        Integer size = userDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = { "/user-data.xml", "/news-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void getAuthorNames() throws Exception {  // TODO: FIX TEST

        Integer expectedSize = 2;
        Integer size = userDAO.getAuthorNames().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void getUserIdByLogin() throws Exception {

        Long expectedId = 4L;
        Long id = userDAO.getUserIdByLogin("Alex");

        Assert.assertEquals(expectedId, id);

    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void add() throws SQLException {
        User user = new User();

        Integer expectedNum = 3;

        user.setLogin("AlexandrOchka1999");
        user.setFirstName("A?");
        user.setLastName("Sasha");
        user.setHashPassword("hashPass1");
        user.setDateOfRegistration(Timestamp.valueOf("2019-05-05 16:10:56.00"));
        user.setRole(Role.ROLE_USER);
        userDAO.add(user);

        Integer num = userDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void read() throws SQLException {

        User user1 = (User) userDAO.getAll().get(0);
        User user2 = userDAO.read(3L);

        Assert.assertEquals(user1, user2);

    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void delete() throws SQLException {

        Integer expectedSize = 1;

        userDAO.delete(4L);

        Integer size = userDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void update() throws SQLException {
        User user = new User();

        user.setUserId(3L);
        user.setLogin("Zaza");
        user.setFirstName("testUpdateName");
        user.setLastName("testUpdateLastName");
        user.setHashPassword("testUpdateHashPass");
        user.setDateOfRegistration(Timestamp.valueOf("2019-11-19 15:03:56.52"));
        user.setRole(Role.ROLE_USER);
        userDAO.update(user);

        Assert.assertEquals(userDAO.getAll().get(1), user);
    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void updateRole() throws SQLException {
        User user = new User();

        user.setUserId(3L);
        user.setLogin("Zaza");
        user.setFirstName("Jaja");
        user.setLastName("Kril");
        user.setHashPassword("smthHash1");
        user.setDateOfRegistration(Timestamp.valueOf("2019-11-19 15:03:56.52"));
        user.setRole(Role.ROLE_ADMIN);
        userDAO.updateRole(3L, 3);

        Assert.assertEquals(userDAO.getAll().get(1), user);
    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void updateData() throws SQLException {
        User user = new User();

        user.setUserId(3L);
        user.setLogin("Zaza");
        user.setFirstName("qwe");
        user.setLastName("asd");
        user.setHashPassword("smthHash1");
        user.setDateOfRegistration(Timestamp.valueOf("2019-11-19 15:03:56.52"));
        user.setRole(Role.ROLE_USER);
        userDAO.updateData(user);

        Assert.assertEquals(userDAO.getAll().get(1), user);
    }

    @Test
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void updatePassword() throws SQLException {
        User user = new User();

        user.setUserId(3L);
        user.setLogin("Zaza");
        user.setFirstName("Jaja");
        user.setLastName("Kril");
        user.setHashPassword("qwerty");
        user.setDateOfRegistration(Timestamp.valueOf("2019-11-19 15:03:56.52"));
        user.setRole(Role.ROLE_USER);
        userDAO.updatePassword(user);

        Assert.assertEquals(userDAO.getAll().get(1), user);
    }

    /*
    TODO: void deactivate add test
     */

    @After
    public void reseter() {
        seqReseter.userSeqReset();
    }

}
