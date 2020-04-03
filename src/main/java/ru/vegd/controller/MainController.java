package ru.vegd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vegd.service.NewsService;
import ru.vegd.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.vegd.controller.PathConstants.PATH_MAIN;

@Controller
public class MainController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showNews(Model model, @RequestParam(value = "page", defaultValue = "1") Long ID) {
        try {
            List<Long> prevList = new ArrayList<>();
            List<Long> nextList = new ArrayList<>();
            for (Long i = ID - 1, z = ID - 4L; z <= i; z++) {
                if (z > 0 && z != ID) {
                    prevList.add(z);
                }
            }
            for (Long i = ID, z = ID + 4L; i <= z; i++) {
                if (i > 0 && i != ID) {
                    nextList.add(i);
                }
            }
            model.addAttribute("page", ID);
            model.addAttribute("prevList", prevList);
            model.addAttribute("nextList", nextList);
            model.addAttribute("news", newsService.getTenNews(ID));
            model.addAttribute("authors", userService.getAuthorNames());
        } catch (Exception e) {
            /*return ERROR;*/
        }
        return PATH_MAIN;
    }

    @GetMapping(value = "/login")
    public String signup() {
        return "login/login";
    }

    @GetMapping(value = "/registration")
    public String registration() {
        return "login/registration";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/error";
    }

}

