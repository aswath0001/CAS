package com.example.CAS.Service;
import com.example.CAS.Entity.Course;
import com.example.CAS.Repository.CourseRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public String bulkUploadCourses(MultipartFile file) throws IOException {
        List<Course> courses = new ArrayList<>();

        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        if (extension.equals("txt")) {
            courses = parseTextFile(file);
        } else if (extension.equals("xlsx")) {
            courses = parseExcelFile(file);
        } else {
            throw new IllegalArgumentException("Only .txt or .xlsx files are supported");
        }

        courseRepo.saveAll(courses);
        return "Successfully uploaded " + courses.size() + " courses";
    }

    private List<Course> parseTextFile(MultipartFile file) throws IOException {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");


                if (data.length != 2) {
                    throw new IllegalArgumentException(
                            "Invalid format at line " + lineNumber + ". Expected: ID,Name"
                    );
                }

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();

                    if (name.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Course name cannot be empty at line " + lineNumber
                        );
                    }

                    courses.add(new Course(id, name));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Invalid ID (must be integer) at line " + lineNumber + ": " + data[0]
                    );
                }
            }
        }
        return courses;
    }

    private List<Course> parseExcelFile(MultipartFile file) throws IOException {
        List<Course> courses = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                courses.add(new Course(
                        (int) row.getCell(0).getNumericCellValue(),
                        row.getCell(1).getStringCellValue()
                ));
            }
        }
        return courses;
    }
}
