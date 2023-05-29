package com.example.hwww.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password_hash")
    private String password_hash;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public User() {
        this.username = "";
        this.email = "";
        this.password_hash = "";
        this.role = "customer";
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public User(String username, String email, String password_hash,
                String role) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = (Timestamp) created_at.clone();
        System.out.println("User created: name: " + username + "; email: " + email
                + "; role: " + role + "; at: " + created_at.toString());
        Logger.getLogger("com.example.hwww.model.User").log(Level.INFO,
                "User created: name: " + username + "; email: " + email
                        + "; role: " + role + "; at: " + created_at.toString());
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getRole() {
        return role;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUsername(String username) {
        this.username = username;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void setEmail(String email) {
        this.email = email;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void setRole(String role) {
        this.role = role;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
}
