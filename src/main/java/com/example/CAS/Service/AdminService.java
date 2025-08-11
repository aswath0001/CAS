package com.example.CAS.Service;

import com.example.CAS.Entity.Admin;
import com.example.CAS.Repository.AdminRepo;
import com.example.CAS.Security.JWTutil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Service
public class AdminService implements UserDetailsService {

    private final AdminRepo adminRepo;

    // Remove JWTutil and PasswordEncoder from here
    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByAdminName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        return new org.springframework.security.core.userdetails.User(
                admin.getAdminName(),
                admin.getPassword(),
                new ArrayList<>()
        );
    }
}