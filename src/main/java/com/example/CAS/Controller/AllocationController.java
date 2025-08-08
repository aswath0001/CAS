package com.example.CAS.Controller;

import com.example.CAS.Entity.Allocation;
import com.example.CAS.Service.AllocationService;
import com.example.CAS.Repository.AllocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocation")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;

    @Autowired
    private AllocationRepo allocationRepo;

    @PostMapping
    public String allocateCourse(@RequestParam String email, @RequestParam int marks) {
        return allocationService.allocateCourse(email, marks);
    }

    @GetMapping("/email/{email}")
    public Allocation getByEmail(@PathVariable String email) {
        return allocationRepo.findByEmail(email);
    }

    @GetMapping("/name/{name}")
    public List<Allocation> getByName(@PathVariable String name) {
        return allocationRepo.findByName(name);
    }

    @GetMapping("/courseName/{courseName}")
    public List<Allocation> getByCourseName(@PathVariable String courseName) {
        return allocationRepo.findByCourseName(courseName);
    }

    @GetMapping("/courseId/{courseId}")
    public List<Allocation> getByCourseId(@PathVariable int courseId) {
        return allocationRepo.findByCourseId(courseId);
    }
}
