/**
 * Класс, представляющий запрос на назначение роли пользователю.
 */
package com.example.hwww.request.user;
import lombok.Data;
@Data
public class GiveRoleRequest {
    /**
     * Электронная почта пользователя, которому нужно назначить роль.
     */
    private String email;
    /**
     * Роль, которую нужно назначить пользователю.
     */
    private String role;

    /*
    *  Секретный ключ для возможности изменять роль пользователя
    * */
    private String secretKey;
}