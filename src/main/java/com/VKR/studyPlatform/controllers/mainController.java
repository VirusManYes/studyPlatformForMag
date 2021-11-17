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
        List<Good> allGoods = goodsDao.getAll();
        model.addAttribute("allGoods", allGoods);
        return "index";
    }

    @GetMapping("/user/{id}")
    public String userById(@PathVariable("id") int id, Model model){
        User user = usersDao.getUser(id);

        model.addAttribute("userId", "User ID = " + user.getUsername());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<User> allUsers = usersDao.getAll("id"); //вставить сюда сортировку
        model.addAttribute("allUsers", allUsers);
        return "allUsers";
    }


}
