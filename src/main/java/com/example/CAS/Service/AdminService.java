package com.example.CAS.Service;

import com.example.CAS.Entity.Admin;
import com.example.CAS.Repository.AdminRepo;
import com.example.CAS.Security.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
@Autowired
    private AdminRepo adminRepo;
@Autowired
    private JWTutil jwTutil;

public String Autenticate(String AdminName,String Password){
    Admin admin = adminRepo.findByAdminName(AdminName)
            .orElseThrow(()-> new RuntimeException("Admin not found"));
    if(!admin.getPassword().equals(Password)){
        throw new RuntimeException("Invalid Password");
    }
    return jwTutil.GenerateToken(AdminName);
}

}
