package com.example.CAS.Security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"AdminName", "password"})
public class AuthRequest {

    @JsonProperty("AdminName")
    private String adminName;

    private String password;

    // Renamed getters/setters for consistency
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}