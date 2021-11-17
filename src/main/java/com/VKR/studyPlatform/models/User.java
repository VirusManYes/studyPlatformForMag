package com.VKR.studyPlatform.models;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    @Id
    //@Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private int id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", columnDefinition = "character varying(15) default ADMIN")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", columnDefinition = "character varying(15) default ACTIVE")
    private Status status;

    @OneToMany(mappedBy = "user")
    private List<Reserve> reserves;

    public User() {
    }

    public int getId(){
        return this.id;
    }


    public Status getIsActive() {
        return status;
    }

    public void setIsActive(Status isActive) {
        this.status = isActive;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = passwordEncoder().encode(password);
    }

    @Override
    public String toString() {
        return  "Username: " + this.getUsername();
    }



    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
