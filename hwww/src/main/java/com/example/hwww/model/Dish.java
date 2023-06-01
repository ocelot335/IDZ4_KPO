/**
 * Модель блюда.
 */
package com.example.hwww.model;
import javax.persistence.*;
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "is_available")
    private boolean isAvailable;
    @Column(name = "price")
    private double price;
    /**
     * Конструктор без параметров.
     */
    public Dish() {
        this.name = "";
        this.description = "";
        this.quantity = -1;
        this.price = -1;
        this.isAvailable = false;
    }
    /**
     * Конструктор с параметрами.
     * @param name Наименование блюда.
     * @param description Описание блюда.
     * @param quantity Количество блюда.
     * @param price Цена блюда.
     * @param isAvailable Доступность блюда.
     */
    public Dish(String name, String description, int quantity, double price, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.isAvailable = isAvailable;
    }
    /**
     * Получение идентификатора блюда.
     * @return Идентификатор блюда.
     */
    public Integer getId() {
        return id;
    }
    /**
     * Получение наименования блюда.
     * @return Наименование блюда.
     */
    public String getName() {
        return name;
    }
    /**
     * Получение доступности блюда.
     * @return Доступность блюда.
     */
    public boolean getAvailable() {
        return isAvailable;
    }
    /**
     * Получение количества блюда.
     * @return Количество блюда.
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Получение описания блюда.
     * @return Описание блюда.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Получение цены блюда.
     * @return Цена блюда.
     */
    public double getPrice() {
        return price;
    }
    /**
     * Установка наименования блюда.
     * @param name Наименование блюда.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Установка описания блюда.
     * @param description Описание блюда.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Установка цены блюда.
     * @param price Цена блюда.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * Установка количества блюда.
     * @param quantity Количество блюда.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Установка доступности блюда.
     * @param available Доступность блюда.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    /**
     * Переопределение метода toString().
     * @return Строка с информацией о блюде.
     */
    @Override
    public String toString() {
        return "Наименование блюда: " + name +
                "\nОписание: " + description +
                "\nКоличество: " + quantity +
                "\nЦена: " + price;
    }
}