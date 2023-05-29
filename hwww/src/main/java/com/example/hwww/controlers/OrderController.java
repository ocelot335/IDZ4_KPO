package com.example.hwww.controlers;

import com.example.hwww.request.GiveRoleRequest;
import com.example.hwww.request.LogInRequest;
import com.example.hwww.request.OrderRequest;
import com.example.hwww.request.RegistrationRequest;
import com.example.hwww.service.OrderService;
import com.example.hwww.service.UserService;
import com.mysql.cj.conf.ConnectionUrlParser;
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

    @PostMapping("/create_order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest request) {
        int status = orderService.createOrder(request.getJWT(), request.getDishes(), request.getSpecialText());
        if (status == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такой пользователь не входил");
        }
        if (status == -2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сессия истекла");
        }

        if (status == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет такого блюда");
        }

        return ResponseEntity.ok("Заказ успешно создан");
    }
}