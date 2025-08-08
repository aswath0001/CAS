package com.example.CAS.Service;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
   public Student addStudent(Student student){
       return studentRepo.save(student);
   }
 public Student getStudentByEmail(String email ){
       return studentRepo.findById(email).orElse(null);
 }
 public List<Student> getAllStudents(){
       return studentRepo.findAll();
 }
    public String deleteStudent( String email) {
        if (studentRepo.existsById(email)) {
            studentRepo.deleteById(email);
            return "Deleted the Student with Email Id: " + email;
        } else {
            return "Student not found with Email Id: " + email;
        }

    }
}
