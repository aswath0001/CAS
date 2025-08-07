package com.example.CAS.Entity;
import jakarta.persistence.*;
import org.springframework.lang.Nullable;

import javax.lang.model.element.Name;

@Entity
@Table(name = "Allocation")
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_No;

    @Column(name = "Name")
    private String name;
    @Column(name = "Email", nullable = false)
    private String email;
    @Column(name = "Course_id",nullable = false)
    private int course_Id;
    @Column(name = "Course_name")
    private String course_Name;

    public int getId_No() {
        return Id_No;
    }

    public void setId_No(int id_No) {
        Id_No = id_No;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCourse_id() {
        return course_Id;
    }

    public void setCourse_id(int course_id) {
        course_Id = course_id;
    }

    public String getCourse_name() {
        return course_Name;
    }

    public void setCourse_name(String course_name) {
        course_Name = course_name;
    }
}
