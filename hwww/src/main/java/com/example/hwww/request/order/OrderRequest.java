/**
 * Класс, представляющий запрос на создание заказа.
 */
package com.example.hwww.request.order;
import lombok.Data;
import java.util.List;
@Data
public class OrderRequest {
    /**
     * JWT токен, используемый для аутентификации пользователя, создающего заказ.
     */
    String JWT;
    /**
     * Список блюд, которые нужно добавить в заказ.
     */
    List<OrderDishRequest> dishes;
    /**
     * Особый текст, который может быть добавлен к заказу.
     */
    String specialText;
}