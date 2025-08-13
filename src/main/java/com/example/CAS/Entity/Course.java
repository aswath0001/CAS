package com.example.CAS.Entity;
import jakarta.persistence.*;

@Entity
@Table (name = "Course")
public class Course {

    @Id
    @Column(name = "Course_id")
    private int id;
   @Column(name = "Course_name")
    private String courseName;
public Course (){

}
public Course (int id,String courseName){
    this.id = id;
    this.courseName = courseName;
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
