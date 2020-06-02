package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vegd.controller.SearchUtils.Paginator;
import ru.vegd.service.NewsService;
import ru.vegd.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ru.vegd.controller.PathConstants.PATH_MAIN;

@Controller
public class MainController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showNews(HttpServletRequest request, Model model, @RequestParam(value = "page", defaultValue = "1") Long id) {
        Paginator paginator = new Paginator(newsService);
        Long numberNewsOnPage = 10L;

        try {
            if (request.getSession().getAttribute("numberNewsOnPage") != null) {
                numberNewsOnPage = Long.parseLong((String) request.getSession().getAttribute("numberNewsOnPage"));
            }

            paginator.configure(
                    id,
                    numberNewsOnPage,
                    null,
                    null
            );

            model.addAttribute("page", id);
            model.addAttribute("prevList", paginator.getPrevPages());
            model.addAttribute("nextList", paginator.getNextPages());
            model.addAttribute("news", newsService.getPaginatedNews(id, numberNewsOnPage));
        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_MAIN;
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/error";
    }

}

