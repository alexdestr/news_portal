package test.ru.vegd.dao.impl;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.After;
import org.junit.Assert;
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

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void getAll() throws SQLException {

        Integer expectedSize = 2;
        Integer size = commentDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void add() throws SQLException {
        Comment comment = new Comment();

        Integer expectedSize = 3;

        comment.setNewsId(3L);
        comment.setAuthorId(3L);
        comment.setCommentText("CommentTestText");
        comment.setSendingDate(Timestamp.valueOf(LocalDateTime.now()));
        commentDAO.add(comment);

        Integer size = commentDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void read() throws SQLException {

        Comment comment1 = (Comment) commentDAO.getAll().get(0);
        Comment comment2 = commentDAO.read(3L);

        Assert.assertEquals(comment1, comment2);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void readLinkedComments() throws SQLException {

        Comment comment1 = (Comment) commentDAO.getAll().get(0);
        Comment comment2 = commentDAO.read(3L);

        Assert.assertEquals(comment1, comment2);

    }

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void delete() throws SQLException {

        Integer expectedSize = 1;

        commentDAO.delete(4L);

        Integer size = commentDAO.getAll().size();

        Assert.assertEquals(expectedSize, size);

    } /*TODO: MAKE TEST FOR DELETE_LINKED()*/

    @Test
    @DatabaseSetup(value = { "/comments-data.xml" }, type = DatabaseOperation.CLEAN_INSERT)
    public void update() throws SQLException {
        Comment comment = new Comment();

        comment.setCommentId(3L);
        comment.setNewsId(3L);
        comment.setAuthorId(3L);
        comment.setCommentText("TestCommentUpdate");
        comment.setSendingDate(Timestamp.valueOf("2020-01-08 15:29:03.18"));

        commentDAO.update(comment);

        Assert.assertEquals(commentDAO.getAll().get(1), comment);

    }

    @After
    public void after() {
        seqReseter.commentSeqReset();
    }

}