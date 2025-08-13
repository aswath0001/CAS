package com.example.CAS.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {

    @Column(name = "Name")
    private String name;

    @Column(name = "Sur_Name")
    private String surName;

    @Id
    @Column(name = "Email")
    private String email;

    @Column(name = "Mark")
    private int mark;
    public Student() {}
public Student(String name ,String surName, String email, int mark){
    this.name = name;
    this.surName = surName;
    this.email = email;
    this.mark = mark;
}

    public String getName(){

        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurName(){

        return surName;
    }

    public void setSurName(String surName){
        this.surName = surName;
    }

    public String getEmail(){

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMark(){

        return mark;
    }


}
