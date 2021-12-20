package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.GoodsDao;
import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.Role;
import com.VKR.studyPlatform.models.Status;
import com.VKR.studyPlatform.models.User;
import com.VKR.studyPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class mainController {


    @Autowired
    private UsersDao usersDao;

    @Autowired
    private GoodsDao goodsDao;

    @GetMapping("/")
    public String mainPage(Model model){
        List<Good> allGoods = goodsDao.getAll((double) 0);

        Double page = Math.ceil(goodsDao.getCount() / 8);

        model.addAttribute("allGoods", allGoods);
        model.addAttribute("reserveGood", new Good());
        model.addAttribute("page", page);
        model.addAttribute("prevPage", 0);
        model.addAttribute("nextPage", 2);
        model.addAttribute("currentPage", 1);
        return "index";
    }

    @GetMapping("/goods/{page}")
    public String goodsPaged(@PathVariable("page") Double currentPage, Model model){
        List<Good> allGoods = goodsDao.getAll((currentPage-1) * 8);

        Double prevPage = currentPage-1;
        Double nextPage = currentPage+1;
        Double page = goodsDao.getCount() / 8;

        model.addAttribute("allGoods", allGoods);
        model.addAttribute("reserveGood", new Good());
        model.addAttribute("page", page);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("currentPage", Math.ceil(currentPage));
        return "index";
    }

    @GetMapping("/user/{id}")
    public String userById(@PathVariable("id") int id, Model model){
        User user = usersDao.getUser(id);

        model.addAttribute("userId", "User ID = " + user.getUsername());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/good/{id}")
    public String goodById(@PathVariable("id") int id, Model model){
        Good good = goodsDao.getGood(id);

        //model.addAttribute("userId", "User ID = " + user.getUsername());
        model.addAttribute("good", good);
        return "good";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> allUsers = usersDao.getAll("id"); //вставить сюда сортировку
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }


}
