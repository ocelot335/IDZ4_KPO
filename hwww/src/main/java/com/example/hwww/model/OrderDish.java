package com.example.hwww.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "order_dish")
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private int orderId;
    @Column(name = "dish_id")
    private int dishId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    public OrderDish() {
        this.orderId = -1;
        this.dishId = -1;
        this.quantity = -1;
        this.price = -1;
    }

    public OrderDish(int orderId, int dishId, int quantity, double price) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getDishId() {
        return dishId;
    }

    public Integer getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
