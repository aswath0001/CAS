package com.example.CAS.Service;
import com.example.CAS.Entity.Course;
import com.example.CAS.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseService {
@Autowired
    private CourseRepo courseRepo;
public Course addCourse(Course course){
    return courseRepo.save(course);
}
public Course getCoureseById (int id){
    return courseRepo.findById(id).orElse(null);
}
public List<Course> getAllCourse (){
    return courseRepo.findAll();
}


}
