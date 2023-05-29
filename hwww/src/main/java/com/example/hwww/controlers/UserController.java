package com.example.hwww.controlers;

import com.example.hwww.request.GiveRoleRequest;
import com.example.hwww.request.LogInRequest;
import com.example.hwww.request.RegistrationRequest;
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
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        int status = userService.register(request.getUsername(), request.getEmail(), request.getPassword());
        if (status == -1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Непредвиденная ошибка");
        }
        if (status == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Введён email неправильного формата");
        }
        if (status == 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Имя пользователя в неправильном формате");
        }
        if (status == 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Слишком маленький пароль");
        }
        if (status == 4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким именем уже есть");
        }
        if (status == 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пользователь с таким email уже существует");
        }

        return ResponseEntity.ok("Пользователь успешно зарегистрирован!");
    }

    @PostMapping("/give_role")
    public ResponseEntity<String> giveRole(@RequestBody GiveRoleRequest request) {
        int status = userService.giveRole(request.getEmail(), request.getRole());
        if (status == -1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Непредвиденная ошибка");
        }
        if (status == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Некорректное значение роли");
        }
        if (status == 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверный формат email");
        }
        if (status == 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет пользователя с таким email");
        }
        return ResponseEntity.ok("Пользователь успешно изменил роль");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogInRequest request) {
        ConnectionUrlParser.Pair<Integer, String> returnFromService = userService.login(request.getEmail(), request.getPassword());
        int status = returnFromService.left;
        if (status == -1) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Непредвиденная ошибка");
        }
        if (status == 2) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверный формат email");
        }
        if (status == 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Слишком маленький пароль");
        }

        if (status == 4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Нет пользователя с таким email");
        }

        if (status == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Пароли не совпадают");
        }

        return ResponseEntity.ok("Вход прошёл успешно;\n ваш JWT:" + returnFromService.right);
    }
}