package com.example.CAS.Security;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Password implements PasswordEncoder {
    private final String salt = "your-static-salt-here";

    @Override
    public String encode(CharSequence rawPassword) {
        return sha256(rawPassword + salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }

    private String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}