package com.example.CAS.Repository;
import com.example.CAS.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Long> {

    Optional<Admin> findByAdminName(String adminName);
}
