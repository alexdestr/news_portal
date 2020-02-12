package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vegd.dao.CommentDAO;
import ru.vegd.entity.Comment;
import ru.vegd.service.CommentService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/h", method = RequestMethod.GET)
    public void showRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.google.ru/");
    }

}
