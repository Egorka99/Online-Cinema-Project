package com.epam.library.model;

import java.io.Serializable;
import java.sql.Date;

public class Author extends Entity implements Serializable {
    private int id;
    private String name;
    private String secondName;
    private String lastName;
    private Date dob;

    public Author(int id, String name, String secondName, String lastName, Date dob) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dob = dob;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setId(int id) {
        this.id = id;
    }
}
