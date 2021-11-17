package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.ReserveDao;
import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.Good;
import com.VKR.studyPlatform.models.Reserve;
import com.VKR.studyPlatform.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

@Controller
public class orderController {

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private UsersDao usersDao;

    @PostMapping("/reserveBook")
    public String addUser(@ModelAttribute("book") Good book, Model model, Principal principal){
        User currentUser = usersDao.getUserByUsername(principal.getName());

        Reserve reserve = new Reserve();
        reserve.setBook(book);
        reserve.setUser(currentUser);
        reserve.setDeadline(getDeadlineToBook());
        reserveDao.saveReserve(reserve);
        return "redirect:/";
    }

    public Date getDeadlineToBook(){
        Calendar deadlineToBook = Calendar.getInstance();
        deadlineToBook.add(Calendar.DATE, 10);
        return deadlineToBook.getTime();
    }

}
