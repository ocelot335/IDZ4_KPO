/**
 * Класс, представляющий блюдо в составе заказа.
 */
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
    /**
     * Конструктор по умолчанию без параметров.
     */
    public OrderDish() {
        this.orderId = -1;
        this.dishId = -1;
        this.quantity = -1;
        this.price = -1;
    }
    /**
     * Конструктор с параметрами.
     * @param orderId - идентификатор заказа
     * @param dishId - идентификатор блюда
     * @param quantity - количество блюд
     * @param price - цена блюда
     */
    public OrderDish(int orderId, int dishId, int quantity, double price) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
        this.price = price;
    }
    /**
     * Метод для получения идентификатора блюда.
     * @return идентификатор блюда
     */
    public int getDishId() {
        return dishId;
    }
    /**
     * Метод для получения идентификатора заказа.
     * @return идентификатор заказа
     */
    public Integer getId() {
        return id;
    }
    /**
     * Метод для получения цены блюда.
     * @return цена блюда
     */
    public double getPrice() {
        return price;
    }
    /**
     * Метод для получения количества блюд.
     * @return количество блюд
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Метод для получения идентификатора заказа.
     * @return идентификатор заказа
     */
    public int getOrderId() {
        return orderId;
    }
    /**
     * Метод для установки количества блюд.
     * @param quantity - количество блюд
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Метод для установки идентификатора блюда.
     * @param dishId - идентификатор блюда
     */
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
    /**
     * Метод для установки цены блюда.
     * @param price - цена блюда
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Метод для установки идентификатора заказа.
     * @param orderId - идентификатор заказа
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}