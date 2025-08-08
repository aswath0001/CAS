package com.example.CAS.Controller;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.StudentRepo;
import com.example.CAS.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/all")

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

   @DeleteMapping("/{email}")
    public String DeleteStudent(@PathVariable String email){
        return studentService.deleteStudent(email);
   }

}
