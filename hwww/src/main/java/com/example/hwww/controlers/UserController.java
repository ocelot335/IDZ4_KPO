/**
 * Контроллер для управления пользователями.
 */
package com.example.hwww.controlers;
import com.example.hwww.exception.JWTException;
import com.example.hwww.exception.RequestException;
import com.example.hwww.exception.RoleException;
import com.example.hwww.request.user.GiveRoleRequest;
import com.example.hwww.request.user.LogInRequest;
import com.example.hwww.request.user.RegistrationRequest;
import com.example.hwww.service.UserService;
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
    /**
     * Сервис для работы с пользователями.
     */
    @Autowired
    private UserService userService;
    /**
     * Регистрация нового пользователя.
     *
     * @param request запрос на регистрацию пользователя
     * @return ответ сервера
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        try {
            userService.register(request.getUsername(), request.getEmail(), request.getPassword());
        } catch (RequestException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        return ResponseEntity.ok("Пользователь успешно зарегистрирован!");
    }
    /**
     * Изменение роли пользователя.
     *
     * @param request запрос на изменение роли пользователя
     * @return ответ сервера
     */
    @PutMapping("/give_role")
    public ResponseEntity<String> giveRole(@RequestBody GiveRoleRequest request) {
        try {
            userService.giveRole(request.getEmail(), request.getRole(), request.getSecretKey());
        } catch (RoleException | RequestException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        return ResponseEntity.ok("Пользователь успешно изменил роль");
    }
    /**
     * Авторизация пользователя.
     *
     * @param request запрос на авторизацию пользователя
     * @return ответ сервера с JWT токеном
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogInRequest request) {
        String JWT;
        try {
            JWT = userService.login(request.getEmail(), request.getPassword());
        } catch (RequestException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        return ResponseEntity.ok("Вход прошёл успешно;\n ваш JWT:" + JWT);
    }
    /**
     * Получение информации о пользователе.
     *
     * @param jwt токен авторизации
     * @param id  идентификатор пользователя
     * @return ответ сервера с информацией о пользователе
     */
    @GetMapping("/get_info")
    public ResponseEntity<String> getInfo(@RequestParam("JWT") String jwt,
                                          @RequestParam("id") int id) {
        String info = null;
        try {
            info = userService.getInfo(jwt, id);
        } catch (JWTException | RoleException | RequestException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
        return ResponseEntity.ok("Информация о пользователе:\n" + info);
    }
}