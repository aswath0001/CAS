package com.example.CAS.Repository;
import com.example.CAS.Entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AllocationRepo extends JpaRepository<Allocation ,Integer> {

    Allocation findByEmail(String email);
    List<Allocation> findByCourseName(String courseName);
    List<Allocation> findByName(String name);
    List<Allocation> findByCourseId(int courseId);
}
