package com.example.CAS.Entity;
import jakarta.persistence.*;

@Entity
@Table (name = "Course")
public class Course {

    @Id
    @Column(name = "Course_id")
    private int Id;
   @Column(name = "Course_name")
    private String courseName;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
