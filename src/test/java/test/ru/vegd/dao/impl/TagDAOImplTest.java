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
import ru.vegd.dao.TagDAO;
import ru.vegd.entity.News;
import ru.vegd.entity.Tag;
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
public class TagDAOImplTest {

    @Autowired
    private TagDAO tagDAO;

    @Autowired
    private SequenceReseter seqReseter;

    private static final Integer flag = 3;

    Tag tag;

    @Before
    public void before() {
        tag = new Tag();
    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" })
    public void getAll() throws SQLException {

        Integer expectedNum = 2;
        Integer num = tagDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" })
    public void add() throws SQLException {

        Integer expectedNum = 3;

        tag.setNews_ID(3L);
        tag.setTags("[Fun][Animals]");
        tagDAO.add(tag);

        Integer num = tagDAO.getAll().size();

        tagDAO.delete(5L);

        Assert.assertEquals(expectedNum, num);
    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" })
    public void read() throws SQLException {

        Tag tag1 = (Tag) tagDAO.getAll().get(0);
        Tag tag2 = tagDAO.read(3L);

        Assert.assertEquals(tag1, tag2);

    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" })
    public void delete() throws SQLException {

        Integer expectedNum = 1;

        tagDAO.delete(4L);

        Integer num = tagDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" })
    public void update() throws SQLException {

        tag.setNews_ID(3L);
        tag.setTags("[Fun][Peoples]");
        tagDAO.update(tag);

        Assert.assertEquals(tagDAO.getAll().get(1), tag);

    }

}

