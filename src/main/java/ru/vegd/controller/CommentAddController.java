package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vegd.entity.Comment;
import ru.vegd.service.CommentService;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ru.vegd.controller.PathConstants.REDIRECT;

@Controller
public class CommentAddController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @PostMapping(value = "news/reply")
    public String doPost(HttpServletRequest request) {

        Long id = (Long) request.getSession().getAttribute("newsID");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String author = auth.getName();

        Comment comment = new Comment();
        comment.setNewsId(id);
        comment.setCommentText(request.getParameter("Comment"));

        try {
            comment.setAuthorId(userService.getUserIdByLogin(author));
            commentService.add(comment);
        } catch (Exception e) {
            /*return ERROR;*/
        }

        return REDIRECT + "news/" + id;
    }

}
