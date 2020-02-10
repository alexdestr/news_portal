package test.ru.vegd.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.vegd.dao.CommentDAO;
import ru.vegd.entity.Comment;
import ru.vegd.service.impl.CommentServiceImpl;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommentsServiceImplTest {
    @Mock
    private CommentDAO commentDAO;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void add() throws SQLException {
        Comment comment = mock(Comment.class);

        commentService.add(comment);

        verify(commentDAO).add(comment);
    }

    @Test
    public void getall() throws SQLException {
        commentService.getAll();

        verify(commentDAO).getAll();
    }

    @Test
    public void read() throws SQLException {
        Long userId = anyLong();

        commentService.read(userId);

        verify(commentDAO).read(userId);
    }

    @Test
    public void delete() throws SQLException {
        Long userId = anyLong();

        commentService.delete(userId);

        verify(commentDAO).delete(userId);
    }

    @Test
    public void update() throws SQLException {
        Comment comment = mock(Comment.class);

        commentService.update(comment);

        verify(commentDAO).update(comment);
    }
}
