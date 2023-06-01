/**
 * Класс, представляющий запрос на регистрацию нового пользователя.
 */
package com.example.hwww.request.user;
import lombok.Data;
@Data
public class RegistrationRequest {
    /**
     * Имя пользователя, которое будет использоваться в системе.
     */
    private String username;
    /**
     * Электронная почта пользователя, которая будет использоваться в системе.
     */
    private String email;
    /**
     * Пароль пользователя, который будет использоваться в системе.
     */
    private String password;
}