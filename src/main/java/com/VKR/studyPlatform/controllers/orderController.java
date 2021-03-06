package com.VKR.studyPlatform.controllers;

import com.VKR.studyPlatform.dao.GoodsDao;
import com.VKR.studyPlatform.dao.OrderDao;
import com.VKR.studyPlatform.dao.ReserveDao;
import com.VKR.studyPlatform.dao.UsersDao;
import com.VKR.studyPlatform.models.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private OrderDao orderDao;

    @PostMapping("/reserveBook/{id}")
    public String addUser(@PathVariable("id") int id, Model model, Principal principal){
        User currentUser = usersDao.getUserByUsername(principal.getName());

        Reserve reserve = new Reserve();
        reserve.setBook(goodsDao.getGood(id));
        reserve.setUser(currentUser);
        reserve.setDeadline(getDeadlineToBook());
        reserve.setCount(1);
        reserve.setCurrentdate(Calendar.getInstance().getTime());
        reserveDao.saveReserve(reserve);
        orderDao.changeCount(ChangeStatus.MINUS, goodsDao.getGood(id));
        return "redirect:/";
    }

    @PostMapping("/realizeReserve")
    public String realizeReserve(@RequestParam(value = "number") int number,
                          @RequestParam(value = "user") int user,
                          @RequestParam(value = "book") int book,
                          Model model){

        Reserve reserve = new Reserve();
        reserve.setNumber(number);
        reserve.setBook(goodsDao.getGood(book));
        reserve.setUser(usersDao.getUser(user));
        reserve.setDeadline(getDeadlineToBook());
        reserve.setCount(-1);
        reserve.setCurrentdate(Calendar.getInstance().getTime());
        reserveDao.saveReserve(reserve);

        Order order = new Order();
        order.setNumber(number);
        order.setBook(goodsDao.getGood(book));
        order.setUser(usersDao.getUser(user));
        order.setDeadline(getDeadlineToBook());
        order.setCurrentdate(Calendar.getInstance().getTime());
        order.setCount(1);
        orderDao.saveOrder(order);


        return "redirect:/";
    }

    @PostMapping("/realizeOrder")
    public String realizeOrder(@RequestParam(value = "number") int number,
                                 @RequestParam(value = "user") int user,
                                 @RequestParam(value = "book") int book,
                                 Model model){

        Order order = new Order();
        order.setNumber(number);
        order.setBook(goodsDao.getGood(book));
        order.setUser(usersDao.getUser(user));
        order.setDeadline(getDeadlineToBook());
        order.setCurrentdate(Calendar.getInstance().getTime());
        order.setCount(-1);
        orderDao.saveOrder(order);
        orderDao.changeCount(ChangeStatus.PLUS, goodsDao.getGood(book));

        return "redirect:/";
    }

    @PostMapping("/deleteReserve")
    public String deleteReserve(@RequestParam(value = "number") int number,
                          @RequestParam(value = "user") int user,
                          @RequestParam(value = "book") int book,
                          Model model){

        Reserve reserve = new Reserve();
        reserve.setNumber(number);
        reserve.setBook(goodsDao.getGood(book));
        reserve.setUser(usersDao.getUser(user));
        reserve.setDeadline(getDeadlineToBook());
        reserve.setCount(-1);
        reserve.setCurrentdate(Calendar.getInstance().getTime());
        reserveDao.saveReserve(reserve);
        return "redirect:/";
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(@RequestParam(value = "number") int number,
                                @RequestParam(value = "user") int user,
                                @RequestParam(value = "book") int book,
                                Model model){

        Order order = new Order();
        order.setNumber(number);
        order.setBook(goodsDao.getGood(book));
        order.setUser(usersDao.getUser(user));
        order.setDeadline(getDeadlineToBook());
        order.setCurrentdate(Calendar.getInstance().getTime());
        order.setCount(-1);
        orderDao.saveOrder(order);
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
        reserve.setCurrentdate(Calendar.getInstance().getTime());
        reserveDao.saveReserve(reserve);
        return "redirect:/";
    }

    @GetMapping("/allReserves")
    public String allReserves(Model model){
        List<Reserve> allReserves = reserveDao.getAll("id"); //???????????????? ???????? ????????????????????
        model.addAttribute("allReserves", allReserves);
        model.addAttribute("showCurrentReserves", true);
        return "allReserves";
    }

    @GetMapping("/allOrders")
    public String allOrders(Model model){
        List<Order> allReserves = orderDao.getAll("id"); //???????????????? ???????? ????????????????????
        model.addAttribute("allReserves", allReserves);
        model.addAttribute("showCurrentReserves", true);
        return "allOrders";
    }

    @GetMapping("/getCurrentReserves")
    public String getCurrentReserves(Model model){
        List<Reserve> currentReserves = reserveDao.getCurrentReserves();
        model.addAttribute("allReserves", currentReserves);
        model.addAttribute("showCurrentReserves", false);
        return "allReserves";
    }

    @GetMapping("/getCurrentOrders")
    public String getCurrentOrders(Model model){
        List<Order> currentReserves = orderDao.getCurrentOrders();
        model.addAttribute("allReserves", currentReserves);
        model.addAttribute("showCurrentReserves", false);
        return "allOrders";
    }

    @GetMapping("/myReserves")
    public String getMyReserves(Model model, Principal principal){
        User currentUser = usersDao.getUserByUsername(principal.getName());

        List<Reserve> currentReserves = reserveDao.getCurrentReserves(currentUser.getId());
        model.addAttribute("allReserves", currentReserves);
        return "myReserves";
    }

    @GetMapping("/myOrders")
    public String getMyOrders(Model model, Principal principal){
        User currentUser = usersDao.getUserByUsername(principal.getName());

        List<Order> currentOrders = orderDao.getCurrentOrders(currentUser.getId());
        model.addAttribute("allReserves", currentOrders);
        return "myOrders";
    }

}
