package test.ru.vegd.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.vegd.dao.NewsDAO;
import ru.vegd.entity.News;
import ru.vegd.service.impl.NewsServiceImpl;

import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    @Mock private NewsDAO newsDAO;

    @InjectMocks private NewsServiceImpl newsService;

    @Test
    public void add() throws SQLException {
        News news = mock(News.class);

        newsService.add(news);

        verify(newsDAO).add(news);
    }

    @Test
    public void getall() throws SQLException {
        newsService.getAll();

        verify(newsDAO).getAll();
    }

    @Test
    public void read() throws SQLException {
        Long userId = anyLong();

        newsService.read(userId);

        verify(newsDAO).read(userId);
    }

    @Test
    public void delete() throws SQLException {
        Long userId = anyLong();

        newsService.delete(userId);

        verify(newsDAO).delete(userId);
    }

    @Test
    public void update() throws SQLException {
        News news = mock(News.class);

        newsService.update(news);

        verify(newsDAO).update(news);
    }
}
