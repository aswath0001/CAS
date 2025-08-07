package com.example.CAS.Repository;
import com.example.CAS.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepo extends JpaRepository<Student,String> {
    Student findByEmail(String Email);
}
