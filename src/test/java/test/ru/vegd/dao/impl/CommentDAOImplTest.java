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
import ru.vegd.dao.CommentDAO;
import ru.vegd.entity.Comment;
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
public class CommentDAOImplTest {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    private SequenceReseter seqReseter;

    private static final Integer flag = 3;

    Comment comment;

    @Before
    public void before() {
        comment = new Comment();
    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" })
    public void getAll() throws SQLException {

        Integer expectedNum = 2;
        Integer num = commentDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" })
    public void add() throws SQLException {

        Integer expectedNum = 3;

        comment.setNews_id(3L);
        comment.setAuthor_id(3L);
        comment.setComment_text("CommentTestText");
        comment.setSending_date(Timestamp.valueOf(LocalDateTime.now()));
        commentDAO.add(comment);

        Integer num = commentDAO.getAll().size();

        commentDAO.delete(5L);

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" })
    public void read() throws SQLException {

        Comment comment1 = (Comment) commentDAO.getAll().get(0);
        Comment comment2 = commentDAO.read(3L);

        Assert.assertEquals(comment1, comment2);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" })
    public void delete() throws SQLException {

        Integer expectedNum = 1;

        commentDAO.delete(4L);

        Integer num = commentDAO.getAll().size();

        Assert.assertEquals(expectedNum, num);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" })
    public void update() throws SQLException {

        comment.setComment_id(3L);
        comment.setNews_id(3L);
        comment.setAuthor_id(3L);
        comment.setComment_text("TestCommentUpdate");
        comment.setSending_date(Timestamp.valueOf("2020-01-08 15:29:03.18"));

        commentDAO.update(comment);

        Assert.assertEquals(commentDAO.getAll().get(1), comment);

    }

    @After
    public void after() {
        seqReseter.commentSeqReset();
    }

}