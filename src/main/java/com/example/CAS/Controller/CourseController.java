package com.example.CAS.Controller;

import com.example.CAS.Entity.Course;
import com.example.CAS.Service.CourseService;
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
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Existing endpoints
    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable int id) {
        return courseService.getCoureseById(id);
    }

    @GetMapping("/all")
    public List<Course> getAllCourse() {
        return courseService.getAllCourse();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload courses in bulk")
    public ResponseEntity<String> uploadCourses(
            @Parameter(description = "Excel (.xlsx) or TXT file containing course data")
            @RequestParam("file") MultipartFile file) {
        try {
            String result = courseService.bulkUploadCourses(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error processing file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}