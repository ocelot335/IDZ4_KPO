/**
 * Класс, представляющий запрос на создание блюда.
 */
package com.example.hwww.request.dish;
import lombok.Data;
@Data
public class DishCreateRequest {
    /**
     * JWT токен для авторизации пользователя.
     */
    String JWT;
    /**
     * Название блюда.
     */
    String name;
    /**
     * Описание блюда.
     */
    String description;
    /**
     * Цена блюда.
     */
    double price;
    /**
     * Количество блюд в заказе.
     */
    int quantity;
    /**
     * Флаг, указывающий на доступность блюда.
     */
    boolean isAvailable;
}