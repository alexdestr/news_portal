package test.ru.vegd.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.vegd.dao.UserDAO;
import ru.vegd.entity.User;
import ru.vegd.service.impl.UserServiceImpl;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock private UserDAO userDAO;

    @InjectMocks private UserServiceImpl userService;

    @Test
    public void add() throws SQLException {
        User user = mock(User.class);

        userService.add(user);

        verify(userDAO).add(user);
    }

    @Test
    public void getall() throws SQLException {
        userService.getAll();

        verify(userDAO).getAll();
    }

    @Test
    public void getAuthorName() throws SQLException {
        userService.getAuthorNames();

        verify(userDAO).getAuthorNames();
    }

    @Test
    public void getUserIdByLogin() throws SQLException {
        String string = anyString();

        userService.getUserIdByLogin(string);

        verify(userDAO).getUserIdByLogin(string);
    }

    @Test
    public void read() throws SQLException {
        Long userId = anyLong();

        userService.read(userId);

        verify(userDAO).read(userId);
    }

    @Test
    public void delete() throws SQLException {
        Long userId = anyLong();

        userService.delete(userId);

        verify(userDAO).delete(userId);
    }

    @Test
    public void update() throws SQLException {
        User user = mock(User.class);

        userService.update(user);

        verify(userDAO).update(user);
    }

    @Test
    public void updateRole() throws SQLException {
        User user = mock(User.class);

        userService.update(user);

        verify(userDAO).update(user);
    }

}
