package com.example.CAS.Service;
import com.example.CAS.Entity.Student;
import com.example.CAS.Repository.StudentRepo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    public String bulkUploadStudents(MultipartFile file) throws IOException {
        List<Student> students = new ArrayList<>();

        // Get file extension
        String filename = file.getOriginalFilename();
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        if (extension.equals("txt")) {
            students = parseTextFile(file);
        } else if (extension.equals("xlsx")) {
            students = parseExcelFile(file);
        } else {
            throw new IllegalArgumentException("Only .txt or .xlsx files are supported");
        }

        studentRepo.saveAll(students);
        return "Successfully uploaded " + students.size() + " students";
    }

    private List<Student> parseTextFile(MultipartFile file) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    students.add(new Student(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            Integer.parseInt(data[3].trim())
                    ));
                }
            }
        }
        return students;
    }
    private List<Student> parseExcelFile(MultipartFile file) throws IOException {
        List<Student> students = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                students.add(new Student(
                        row.getCell(0).getStringCellValue(),
                        row.getCell(1).getStringCellValue(),
                        row.getCell(2).getStringCellValue(),
                        (int) row.getCell(3).getNumericCellValue()
                ));
            }
        }
        return students;
    }
}

