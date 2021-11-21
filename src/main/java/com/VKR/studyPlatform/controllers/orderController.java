package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.GoodsDao;
import com.VKR.studyPlatform.dao.ReserveDao;
import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class orderController {

    @Autowired
    private ReserveDao reserveDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private GoodsDao goodsDao;

    @PostMapping("/reserveBook/{id}")
    public String addUser(@PathVariable("id") int id, Model model, Principal principal){
        User currentUser = usersDao.getUserByUsername(principal.getName());

        Reserve reserve = new Reserve();
        reserve.setBook(goodsDao.getGood(id));
        reserve.setUser(currentUser);
        reserve.setDeadline(getDeadlineToBook());
        reserve.setCount(1);
        reserveDao.saveReserve(reserve);
        return "redirect:/";
    }

    @PostMapping("/realizeReserve")
    public String addUser(@RequestParam(value = "number") int number,
                          @RequestParam(value = "user") int user,
                          @RequestParam(value = "book") int book,
                          Model model, Principal principal){

        Reserve reserve = new Reserve();
        reserve.setNumber(number);
        reserve.setBook(goodsDao.getGood(book));
        reserve.setUser(usersDao.getUser(user));
        reserve.setDeadline(getDeadlineToBook());
        reserve.setCount(-1);
        reserveDao.saveReserve(reserve);
        return "redirect:/";
    }

    public Date getDeadlineToBook(){
        Calendar deadlineToBook = Calendar.getInstance();
        deadlineToBook.add(Calendar.DATE, 10);
        return deadlineToBook.getTime();
    }

    @PostMapping("/addGoodAdmin")
    public String addBookFromAdmin(Model model){

        Good good = new Good();
        good.setName("test");
        good.setDefinition("test ins");
        goodsDao.saveGood(good);
        return "redirect:/";
    }

    @PostMapping("/addReserveAdmin")
    public String addReserveFromAdmin(Model model, Principal principal){
        User currentUser = usersDao.getUserByUsername(principal.getName());

        Reserve reserve = new Reserve();
        reserve.setUser(currentUser);
        reserve.setBook(goodsDao.getGood(1));
        reserve.setDeadline(getDeadlineToBook());
        reserveDao.saveReserve(reserve);
        return "redirect:/";
    }

    @GetMapping("/allReserves")
    public String allReserves(Model model){
        List<Reserve> allReserves = reserveDao.getAll("id"); //вставить сюда сортировку
        model.addAttribute("allReserves", allReserves);
        model.addAttribute("showCurrentReserves", true);
        return "allReserves";
    }

    @GetMapping("/getCurrentReserves")
    public String getCurrentReserves(Model model){
        List<Reserve> currentReserves = reserveDao.getCurrentReserves();
        model.addAttribute("allReserves", currentReserves);
        model.addAttribute("showCurrentReserves", false);
        return "allReserves";
    }

}
