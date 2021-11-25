package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.Role;
import com.VKR.studyPlatform.models.Status;
import com.VKR.studyPlatform.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class authController {

    @Autowired
    private UsersDao usersDao;

    @GetMapping("/register")
    public String goToRegister(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/fail")
    public String goToFail(Model model){
        return "fail";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") User user, Model model){
        usersDao.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/registerAdmin")
    public String addUserFromAdmin(@ModelAttribute("username") String username, Model model){
        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(username);
        user.setPassword("asdasd");
        user.setIsActive(Status.ACTIVE);
        usersDao.saveUser(user);
        return "redirect:/";
    }

    @PostMapping("/makeBan")
    public String makeBan(@ModelAttribute("userId") String userId){
        User user = usersDao.getUser(Integer.parseInt(userId));
        if(user == null){
            return "fail";
        }
        user.setIsActive(user.getIsActive().equals(Status.ACTIVE)?Status.BANNED:Status.ACTIVE);
        usersDao.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String goToLogin(Model model){
        return "login";
    }


}
