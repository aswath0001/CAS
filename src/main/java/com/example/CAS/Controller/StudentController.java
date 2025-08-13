package com.example.CAS.Controller;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.StudentRepo;
import com.example.CAS.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload students in bulk")
    public ResponseEntity<String> uploadStudents(
            @Parameter(description = "Excel (.xlsx) or TXT file containing student data")
            @RequestParam("file") MultipartFile file) {

        try {
            String result = studentService.bulkUploadStudents(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
