package com.example.CAS.Controller;
import com.example.CAS.Repository.CourseRepo;
import com.example.CAS.Entity.Course;
import com.example.CAS.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Course")

public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }
    @GetMapping("/{id}")
    public  Course getCourse(@PathVariable int id){
        return courseService.getCoureseById(id);
    }
    @GetMapping("/all")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();


    }

}
