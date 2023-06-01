/**
 * Этот класс представляет собой контроллер REST API для управления заказами.
 * Версия API по умолчанию - v1.
 */
package com.example.hwww.controlers;
import com.example.hwww.exception.JWTException;
import com.example.hwww.exception.RequestException;
import com.example.hwww.exception.RoleException;
import com.example.hwww.request.order.OrderRequest;
import com.example.hwww.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * Создает новый заказ на основе переданного запроса.
     * @param request - объект запроса на создание заказа.
     * @return ResponseEntity с кодом состояния и сообщением об успешном создании заказа и его номером, или ошибкой и ее сообщением.
     */
    @PostMapping("/create_order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        int orderId;
        try {
            orderId = orderService.createOrder(request.getJWT(), request.getDishes(), request.getSpecialText());
        } catch (JWTException | RequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok("Заказ успешно создан, его номер: " + orderId);
    }
    /**
     * Получает информацию о заказе по его номеру и JWT.
     * @param jwt - JWT для аутентификации пользователя.
     * @param id - номер заказа.
     * @return ResponseEntity с кодом состояния и информацией о заказе, или ошибкой и ее сообщением.
     */
    @GetMapping("/get_info_about_order")
    public ResponseEntity<String> getInfoAboutOrder(@RequestParam("JWT") String jwt, @RequestParam("id") int id) {
        String info;
        try {
            info = orderService.getInfo(jwt, id);
        } catch (JWTException | RequestException | RoleException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(info);
    }
    /**
     * Получает меню ресторана.
     * @return ResponseEntity с кодом состояния и меню ресторана, или ошибкой и ее сообщением.
     */
    @GetMapping("/get_menu")
    public ResponseEntity<String> getMenu() {
        return ResponseEntity.ok(orderService.getMenu());
    }
}