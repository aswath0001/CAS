package com.example.CAS.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthControl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTutil jwTutil;

    @PostMapping("/authenticate")

    public AuthResponse authenticate(@RequestBody AuthRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password");
        }

        String token = jwTutil.GenerateToken(request.getUserName());
        return new AuthResponse(token);
        }
    }

