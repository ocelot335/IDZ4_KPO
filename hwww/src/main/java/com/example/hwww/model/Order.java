package com.example.hwww.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс Order представляет заказ веб-приложения.
 * Он содержит свойства для идентификатора заказа, идентификатора пользователя,
 * статуса заказа, специальных запросов и отметок времени создания и последнего обновления заказа.
 * Аннотации выше каждого свойства определяют, как оно должно быть отображено в таблице базы данных.
 * В данном случае таблица называется "order" (с кавычками).
 */
@Entity
@Table(name = "\"order\"")
public class Order {
    /**
     * Идентификатор заказа.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Идентификатор пользователя.
     */
    @Column(name = "user_id")
    private int userId;
    /**
     * Статус заказа.
     */
    @Column(name = "status")
    private String status;
    /**
     * Специальные запросы.
     */
    @Column(name = "special_requests")
    private String specialText;
    /**
     * Отметка времени создания заказа.
     */
    @Column(name = "created_at")
    private Timestamp created_at;
    /**
     * Отметка времени последнего обновления заказа.
     */
    @Column(name = "updated_at")
    private Timestamp updated_at;
    /**
     * Конструктор без параметров, устанавливающий значения свойств по умолчанию.
     */
    public Order() {
        this.userId = -1;
        this.status = "";
        this.specialText = "";
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Конструктор с параметрами, устанавливающий значения свойств заказа.
     * @param userId - идентификатор пользователя
     * @param status - статус заказа
     * @param specialText - специальные запросы
     */
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
    /**
     * Метод для получения идентификатора заказа.
     * @return идентификатор заказа
     */
    public Integer getId() {
        return id;
    }
    /**
     * Метод для получения идентификатора пользователя.
     * @return идентификатор пользователя
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Метод для получения специальных запросов.
     * @return специальные запросы
     */
    public String getSpecialText() {
        return specialText;
    }
    /**
     * Метод для получения статуса заказа.
     * @return статус заказа
     */
    public String getStatus() {
        return status;
    }
    /**
     * Метод для получения отметки времени создания заказа.
     * @return отметка времени создания заказа
     */
    public Timestamp getCreated_at() {
        return created_at;
    }
    /**
     * Метод для получения отметки времени последнего обновления заказа.
     * @return отметка времени последнего обновления заказа
     */
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    /**
     * Метод для установки идентификатора пользователя.
     * @param userId - идентификатор пользователя
     */
    public void setUserId(int userId) {
        this.userId = userId;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Метод для установки специальных запросов.
     * @param specialText - специальные запросы
     */
    public void setSpecialText(String specialText) {
        this.specialText = specialText;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Метод для установки статуса заказа.
     * @param status - статус заказа
     */
    public void setStatus(String status) {
        this.status = status;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }
}