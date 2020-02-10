package test.ru.vegd.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.vegd.dao.TagDAO;
import ru.vegd.entity.Tag;
import ru.vegd.service.impl.TagServiceImpl;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TagsServiceImplTest {
    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private TagServiceImpl tagService;

    @Test
    public void add() throws SQLException {
        Tag tag = mock(Tag.class);

        tagService.add(tag);

        verify(tagDAO).add(tag);
    }

    @Test
    public void getall() throws SQLException {
        tagService.getAll();

        verify(tagDAO).getAll();
    }

    @Test
    public void read() throws SQLException {
        Long userId = anyLong();

        tagService.read(userId);

        verify(tagDAO).read(userId);
    }

    @Test
    public void delete() throws SQLException {
        Long userId = anyLong();

        tagService.delete(userId);

        verify(tagDAO).delete(userId);
    }

    @Test
    public void update() throws SQLException {
        Tag tag = mock(Tag.class);

        tagService.update(tag);

        verify(tagDAO).update(tag);
    }
}
