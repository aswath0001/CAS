package com.example.CAS.Controller;
import com.example.CAS.Entity.Admin;
import com.example.CAS.Service.AdminService;
import com.example.CAS.Security.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private  JWTutil jwTutil;
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Admin adminRequest) {
        String userName = adminRequest.getAdminName();
        String password = adminRequest.getPassword();

        if (adminService.validateAdmin(userName, password)) {
            String token = adminService.autenticate(userName, password);
            return ResponseEntity.ok().body("JWT Token:" + token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

}
