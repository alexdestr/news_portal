package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.zaxxer.hikari.HikariConfig;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionTemplate;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/*TODO: Make init db for test with @DatabaseSetup*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/springTest.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@Transactional
public class UserDAOImplTest{

    @Autowired
    UserDAO userDAO;

    @Autowired
    DataSource dataSource;

    User user;

    @Before
    public void before() throws SQLException, DatabaseUnitException {
        user = new User();
    }

    @Test
    @DatabaseSetup(value = "/user-data.xml")
    public void getAll() throws Exception {

        List<User> persons = userDAO.getAll();

        System.out.println(userDAO.getAll());
        System.out.println("asdsadasdadadadasasdasdad");

    }

    @Test
    @Rollback(true)
    public void add() throws SQLException {

        User user = new User();
        user.setLogin("AlexandrOchka1999");
        user.setUser_name("A?");
        user.setUser_last_name("Sasha");
        user.setHash_password("hashPass1");
        user.setDate_of_registration(Timestamp.valueOf(LocalDateTime.now()));
        userDAO.add(user);
        System.out.println(userDAO.getAll());
        System.out.println("----------");


    }

    @Test
    public void read() {


    }

    @Test
    public void delete() {

    }

    @Test
    public void update() {
    }
}