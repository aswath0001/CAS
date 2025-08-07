package com.example.CAS.Controller;
import com.example.CAS.Repository.CourseRepo;
import com.example.CAS.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Course")

public class CourseController {
    @Autowired
    private CourseRepo courseRepo;
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return courseRepo.save(course);
    }
    @GetMapping("/{id}")
    public  Course getCourse(@PathVariable int id){
        return courseRepo.findById(id).orElse(null);
    }
    @GetMapping("/all")
    public List<Course> getAllCourse(){
        return courseRepo.findAll();


    }

}
