package com.example.hwww.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "session_token")
    private String token;

    @Column(name = "expires_at")
    private Timestamp expiresAt;

    public Session() {
        this.userId = -1;
        this.token = "";
        this.expiresAt = new Timestamp(System.currentTimeMillis());
    }

    public Session(int userId, String token) {
        this.userId = userId;
        this.token = token;
        this.expiresAt = new Timestamp(System.currentTimeMillis() + 1000 * 60 * 10);//10 Minutes
        Logger.getLogger("com.example.hwww.model.Session").log(Level.INFO,
                "Session started: userId: " + userId
                        + "; expires at: " + expiresAt.toString());
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
