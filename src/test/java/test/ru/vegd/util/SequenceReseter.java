package test.ru.vegd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class SequenceReseter {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SequenceReseter.class.getName());

    @Autowired
    DataSource dataSource;

    private static final String SQL_RESET_USERS_SEQ = "ALTER SEQUENCE tests.users_user_id_seq RESTART";
    private static final String SQL_RESET_NEWS_SEQ = "ALTER SEQUENCE tests.news_news_id_seq RESTART";
    private static final String SQL_RESET_COMMENTS_SEQ = "ALTER SEQUENCE tests.comments_comments_id_seq RESTART";

    public void userSeqReset() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(SQL_RESET_USERS_SEQ);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Sequence reset eror");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.warn("Sequence reset eror");
            }
        }
    }

    public void newsSeqReset() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(SQL_RESET_NEWS_SEQ);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Sequence reset eror");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void commentSeqReset() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(SQL_RESET_COMMENTS_SEQ);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Sequence reset eror");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                logger.warn("Sequence reset eror");
            }
        }
    }

}
