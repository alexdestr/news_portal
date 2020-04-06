package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import ru.vegd.dao.TagDAO;
import ru.vegd.entity.Tag;
import test.ru.vegd.TestConfig;

import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class TagDAOImplTest {

    @Autowired
    private TagDAO tagDAO;

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void getAll() throws SQLException {

        Integer expectedSize = 2;
        Integer size = tagDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void add() throws SQLException {
        Tag tag = new Tag();

        Integer expectedSize = 3;

        tag.setNewsID(3L);
        tag.setTags("[Fun][Animals]");
        tagDAO.add(tag);

        Integer size = tagDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);
    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void read() throws SQLException {

        Tag tag1 = (Tag) tagDAO.getAll().get(0);
        Tag tag2 = tagDAO.read(3L).get(0);

        Assert.assertEquals(tag1, tag2);

    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void delete() throws SQLException {

        Integer expectedSize = 1;

        tagDAO.delete(4L);

        Integer size = tagDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = { "/tag-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void update() throws SQLException {
        Tag tag = new Tag();

        tag.setNewsID(3L);
        tag.setTags("[Fun][Peoples]");
        tagDAO.update(tag);

        Assert.assertEquals(tagDAO.getAll().get(1), tag);

    }

}

