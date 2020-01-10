package ru.vegd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class, args);
    }
}