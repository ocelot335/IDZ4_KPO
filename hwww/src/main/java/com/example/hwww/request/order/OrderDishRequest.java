/**
 * Класс, представляющий запрос на добавление блюда в заказ.
 */
package com.example.hwww.request.order;
import lombok.Data;
@Data
public class OrderDishRequest {
    /**
     * Идентификатор блюда, которое нужно добавить в заказ.
     */
    int dishId;
    /**
     * Количество порций блюда, которые нужно добавить в заказ.
     */
    int quantity;
}