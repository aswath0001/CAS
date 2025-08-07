package com.example.CAS.Controller;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Students")
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentRepo.save(student);
    }
    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email){
        return studentRepo.findById(email).orElse(null);
    }
    @GetMapping("/all")

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
@DeleteMapping("/{email}")
    public  String deleteStudent(@PathVariable String email){
    if (studentRepo.existsById(email)) {
        studentRepo.deleteById(email);
        return "Deleted the Student with Email Id: " + email;
    } else {
        return "Student not found with Email Id: " + email;
    }
}
}
