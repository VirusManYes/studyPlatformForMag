package com.VKR.studyPlatform.models;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "goods")
public class Good implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "definition")
    private String definition;
    @Column(name = "\"bigInfo\"")
    private String bigInfo;

    @Column
    private int bookCount;

    public Good() {
    }


    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }
    public String getUIDefinition() {
        String result = this.definition;
        if(result.length() > 200){
            result = this.definition.substring(0, 200);
        }
        return result;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getBigInfo() {
        return bigInfo;
    }

    public void setBigInfo(String bigInfo) {
        this.bigInfo = bigInfo;
    }



    @Override
    public String toString() {
        return "Good:".concat(this.getName());
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
