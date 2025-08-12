package com.example.CAS.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthControl {

    private final AuthenticationManager authenticationManager;
    private final JWTutil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthControl(AuthenticationManager authenticationManager,
                       JWTutil jwtUtil,
                       PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {
        System.out.println("=== AUTH REQUEST ===");
        System.out.println("AdminName: " + request.getAdminName());
        System.out.println("Password: " + request.getPassword());

        try {

            boolean manualMatch = passwordEncoder.matches("12345",
                    "2e4a8566d05b74ca91a0f6d9596b03219586bb936662d00a6230cf5ae4ef29f3");
            System.out.println("Manual Password match: " + manualMatch);

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getAdminName(),
                            request.getPassword()
                    )
            );

            String token = jwtUtil.GenerateToken(authentication.getName());
            System.out.println("AUTH SUCCESS - Token generated");
            return ResponseEntity.ok(new AuthResponse(token));

        } catch (BadCredentialsException e) {
            System.out.println("AUTH FAILED: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials - DEBUG: " + e.getMessage());
        }
    }
}