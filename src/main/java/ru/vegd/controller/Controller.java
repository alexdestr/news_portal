package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vegd.dao.CommentDAO;
import ru.vegd.entity.Comment;
import ru.vegd.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/h", method = RequestMethod.GET)
    public void showRegister(HttpServletRequest request, HttpServletResponse response) {
        try {
            Comment comment = new Comment();
            comment.setNews_id(1L);
            comment.setAuthor_id(4L);
            comment.setComment_text("nice");
            commentService.add(comment);
            System.out.println(commentService.getAll());
        } catch (SQLException e) {
            System.out.println("Osujdayu");
        }
    }
}
