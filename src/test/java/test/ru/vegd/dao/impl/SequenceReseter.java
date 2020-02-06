package test.ru.vegd.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class SequenceReseter {

    @Autowired
    private DataSource dataSource;

    public static void userSeqReset() {

    }

    public static void newsSeqReset() {

    }

    public static void commentSeqReset() {

    }

}
