package com.VKR.studyPlatform.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private int number;

    @Column(name = "count")
    private int count;


    @ManyToOne(targetEntity = Good.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book")
    private Good book;

    //@Column(name = "user")
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user\"")
    private User user;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "currentdate")
    private Date currentdate;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Good getBook() {
        return book;
    }

    public void setBook(Good book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate) {
        this.currentdate = currentdate;
    }

    @Override
    public String toString() {
            return "Order:".concat(this.getBook().getName()).concat(" From user:").concat(this.getUser().getUsername());
        }
    }



