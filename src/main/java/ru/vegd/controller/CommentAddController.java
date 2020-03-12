package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.Comment;
import ru.vegd.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.vegd.controller.PathConstants.ERROR;
import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class CommentAddController {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "news/reply")
    public String doPost(HttpServletRequest request) {

        Long id = (Long) request.getSession().getAttribute("newsID");

        Comment comment = new Comment();
        comment.setNews_id(id);
        comment.setAuthor_id(2L);
        comment.setComment_text(request.getParameter("Comment"));

        try {
            commentService.add(comment);
        } catch (Exception e) {
            /*return ERROR;*/
        }

        return REDIRECT + "news/" + id;
    }

}
