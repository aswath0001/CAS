package com.example.CAS.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Allocation")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNo;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Course_id", nullable = false)
    private int courseId;

    @Column(name = "Course_name")
    private String courseName;

    // Getters and Setters
    public int getIdNo() {
        return idNo;
    }

    public void setIdNo(int idNo) {
        this.idNo = idNo;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
