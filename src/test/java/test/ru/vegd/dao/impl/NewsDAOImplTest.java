package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ru.vegd.dao.NewsDAO;
import ru.vegd.entity.News;
import test.ru.vegd.TestConfig;
import test.ru.vegd.util.SequenceReseter;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class NewsDAOImplTest {

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private SequenceReseter seqReseter;

    private static final Integer flag = 3;

    News news;

    @Before
    public void before() {
        news = new News();
    }

    @Test
    @DatabaseSetup(value = { "/news-data.xml" })
    public void getAll() throws SQLException {

        Integer expectedNum = 2;
        Integer num = newsDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/news-data.xml" })
    public void add() throws SQLException {

        Integer expectedNum = 3;

        news.setAuthor_id(3L);
        news.setTittle("Test");
        news.setNews_text("SmthText1234");
        news.setPublic_date(Timestamp.valueOf(LocalDateTime.now()));
        newsDAO.add(news);

        Integer num = newsDAO.getAll().size();

        newsDAO.delete(5L);

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/news-data.xml" })
    public void read() throws SQLException {

        News news1 = (News) newsDAO.getAll().get(0);
        News news2 = newsDAO.read(3L);

        Assert.assertEquals(news1, news2);

    }

    @Test
    @DatabaseSetup(value = { "/news-data.xml" })
    public void delete() throws SQLException {

        Integer expectedNum = 1;

        newsDAO.delete(4L);

        Integer num = newsDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/news-data.xml" })
    public void update() throws SQLException {

        news.setNews_id(3L);
        news.setAuthor_id(3L);
        news.setTittle("TestUpdate");
        news.setNews_text("Test Update Successful Done");
        news.setPublic_date(Timestamp.valueOf("2020-01-01 11:02:22.12"));
        newsDAO.update(news);

        Assert.assertEquals(newsDAO.getAll().get(1), news);

    }

    @After
    public void reseter() {
        seqReseter.newsSeqReset();
    }
}