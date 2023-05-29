package com.example.hwww.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "status")
    private String status;
    @Column(name = "special_requests")
    private String specialText;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;

    public Order() {
        this.userId = -1;
        this.status = "";
        this.specialText = "";
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public Order(int userId, String status, String specialText) {
        this.userId = userId;
        this.status = status;
        this.specialText = specialText;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = (Timestamp) created_at.clone();
        System.out.println("Order created: userId: " + userId + "; status: " + status
                + "; special text: " + specialText + "; at: " + created_at.toString());
        Logger.getLogger("com.example.hwww.model.User").log(Level.INFO,
                "Order created: userId: " + userId + "; status: " + status
                        + "; special text: " + specialText + "; at: " + created_at.toString());
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getSpecialText() {
        return specialText;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void setSpecialText(String specialText) {
        this.specialText = specialText;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    public void setStatus(String status) {
        this.status = status;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
}
