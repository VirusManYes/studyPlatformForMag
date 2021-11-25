package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.GoodsDao;
import com.VKR.studyPlatform.dao.OrderDao;
import com.VKR.studyPlatform.dao.ReserveDao;
import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class bookController {

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private OrderDao orderDao;

    @PostMapping("/deleteBookAndGoMain/{id}")
    public String deleteBookAndGoMain(@PathVariable(value = "id") int bookId,
                             Model model){
        goodsDao.deleteBook(goodsDao.getGood(bookId));
       return "redirect:/";
    }

    @PostMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") int bookId,
                             Model model){
        goodsDao.deleteBook(goodsDao.getGood(bookId));
        return "redirect:/allBooks";
    }

    @GetMapping("/allBooks")
    public String allBooks(Model model){
        List<Good> allBooks = goodsDao.getAll();

        model.addAttribute("allBooks", allBooks);
        model.addAttribute("changeCount", false);
        return "allBooks";
    }

    @GetMapping("/changeBookCount")
    public String changeBookCount(Model model){
        List<Good> allBooks = goodsDao.getAll();

        model.addAttribute("allBooks", allBooks);
        model.addAttribute("changeCount", true);
        return "allBooks";
    }



}
