package com.VKR.studyPlatform.models;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goods")
public class Good {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "definition")
    private String definition;
    @Column(name = "\"bigInfo\"")
    private String bigInfo;



    public Good() {
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
}
