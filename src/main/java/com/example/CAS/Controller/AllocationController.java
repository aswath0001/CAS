package com.example.CAS.Controller;

import com.example.CAS.Entity.Allocation;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.AllocationRepo;
import com.example.CAS.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AllocationController {

    @Autowired
    private AllocationRepo allocationRepo;

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/Allocation")
    public String allocateCourse(@RequestParam String email, @RequestParam int marks) {

        Student student = studentRepo.findByEmail(email);

        if (student == null) {
            return "Student not found with email: " + email;
        }

        // Step 2: Determine course
        String courseName;
        int courseId;

        if (marks >= 95) {
            courseName = "CSE";
            courseId = 1;
        } else if (marks >= 90) {
            courseName = "IT";
            courseId = 2;
        } else if (marks >= 85) {
            courseName = "ECE";
            courseId = 3;
        } else if (marks >= 80) {
            courseName = "EEE";
            courseId = 4;
        } else {
            return "No course available for mark: " + marks;
        }


        Allocation allocation = new Allocation();
        allocation.setName(student.getName());
        allocation.setEmail(student.getEmail());
        allocation.setCourse_name(courseName);
        allocation.setCourse_id(courseId);

        allocationRepo.save(allocation);

        return "Student allocated to course: " + courseName;
    }
    @GetMapping("allocation/email/{email}")
    public Allocation getByEmail(@PathVariable String email){
        return allocationRepo.findByEmail(email);
    }
    @GetMapping("allocation/name/{name}")
    public List<Allocation> getByName(@PathVariable String name){
        return allocationRepo.findByName(name);
    }
    @GetMapping("allocation/courseName{courseName}")
    public  List<Allocation> getByCourseName(@PathVariable String courseName){
        return allocationRepo.findByCourseName(courseName);
    }
    @GetMapping("Allocation/courseId{courseId}")
    public  List<Allocation> getByCourseId(@PathVariable int courseId){
        return allocationRepo.findByCourse_Id(courseId);
    }

}
