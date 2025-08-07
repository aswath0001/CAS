package com.example.CAS.Repository;
import com.example.CAS.Entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AllocationRepo extends JpaRepository<Allocation ,Integer> {
}
