package com.example.CAS.Service;
import com.example.CAS.Entity.Allocation;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.AllocationRepo;
import com.example.CAS.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AllocationService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private AllocationRepo allocationRepo;

    public String allocateCourse(String email,int Mark){
        Student student = studentRepo.findByEmail(email);
        if(student == null){
            return "Student not found with email"+email;
        }
        String courseName;
        int courseId;
        if (Mark >= 95) {
            courseName = "CSE";
            courseId = 1;
        } else if (Mark >= 90) {
            courseName = "IT";
            courseId = 2;
        } else if (Mark >= 85) {
            courseName = "ECE";
            courseId = 3;
        } else if (Mark >= 80) {
            courseName = "EEE";
            courseId = 4;
        } else if(Mark>=75){
            courseName = "Mech";
            courseId =5;
        }
            else {
            return "No course available for mark: " + Mark;
        }
        Allocation allocation = new Allocation();
        allocation.setName(student.getName());
        allocation.setEmail(student.getEmail());
        allocation.setCourseName(courseName);
        allocation.setCourseId(courseId);

        allocationRepo.save(allocation);

        return "Student allocated to course: " + courseName;
    }
    }

