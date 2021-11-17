package com.VKR.studyPlatform.models;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "reserve")
public class Reserve {

    @Id
    @Column(name = "number")
    private int number;

    //@Column(name = "book")
    @ManyToOne
    private Good book;

    //@Column(name = "user")
    @ManyToOne
    private User user;

    @Column(name = "deadline")
    private Date deadline;

    public Reserve() {
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

    @Override
    public String toString() {
            return "Reserve:".concat(this.getBook().getName()).concat(" From user:").concat(this.getUser().getUsername());
        }
    }


