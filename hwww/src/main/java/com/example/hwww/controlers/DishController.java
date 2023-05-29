package com.example.hwww.controlers;

import com.example.hwww.repository.DishRepository;
import com.example.hwww.request.DishRequest;
import com.example.hwww.request.OrderRequest;
import com.example.hwww.service.DishService;
import com.example.hwww.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishRepository dishRepository;

    @PostMapping("/create_dish")
    public ResponseEntity<String> createDish(@RequestBody DishRequest request) {
        int status = dishService.createDish(request.getJWT(), request.getName(),
                request.getDescription(), request.getPrice(), request.getQuantity());
        if (status == -1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такой пользователь не входил");
        }
        if (status == -2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сессия истекла");
        }

        if (status == -3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Только менеджер может изменять меню");
        }

        return ResponseEntity.ok("Блюдо успешно создано");
    }
}
