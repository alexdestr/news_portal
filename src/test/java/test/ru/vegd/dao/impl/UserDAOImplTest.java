package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.dbunit.DatabaseUnitException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
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

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
    @DatabaseSetup(value = "/user-data.xml", type = DatabaseOperation.CLEAN_INSERT)
    public void add() throws SQLException {
        User user = new User();

        Integer expectedNum = 3;

        user.setLogin("AlexandrOchka1999");
        user.setUser_name("A?");
        user.setUser_last_name("Sasha");
        user.setHash_password("hashPass1");
        user.setDate_of_registration(Timestamp.valueOf("2019-05-05 16:10:56.00"));
        user.setRole(Role.USER);
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

        user.setUser_id(3L);
        user.setLogin("Zaza");
        user.setUser_name("testUpdateName");
        user.setUser_last_name("testUpdateLastName");
        user.setHash_password("testUpdateHashPass");
        user.setDate_of_registration(Timestamp.valueOf("2019-11-19 15:03:56.52"));
        user.setRole(Role.USER);
        userDAO.update(user);

        Assert.assertEquals(userDAO.getAll().get(1), user);
    }

    @After
    public void reseter() {
        seqReseter.userSeqReset();
    }

}
