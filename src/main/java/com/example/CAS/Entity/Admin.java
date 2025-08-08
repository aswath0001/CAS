package com.example.CAS.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;

    @Column(name = "AdminName",nullable = false)
    private String adminName;

    @Column(name = "Password",nullable = false)
    private String password;

    public long getID() {
        return iD;
    }

    public void setID(long ID) {
        this.iD = ID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String AdminName) {
        adminName = AdminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        password = Password;
    }
}
