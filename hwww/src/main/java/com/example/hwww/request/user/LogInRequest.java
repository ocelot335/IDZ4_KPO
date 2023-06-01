/**
 * Класс, представляющий запрос на вход пользователя в систему.
 */
package com.example.hwww.request.user;
import lombok.Data;
@Data
public class LogInRequest {
    /**
     * Электронная почта пользователя, который пытается войти в систему.
     */
    String email;
    /**
     * Пароль пользователя, который пытается войти в систему.
     */
    String password;
}