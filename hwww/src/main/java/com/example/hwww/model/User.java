/**
 * Класс, представляющий сущность "Пользователь".
 */
package com.example.hwww.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Уникальный идентификатор пользователя.
    @Column(name = "username")
    private String username; // Имя пользователя.
    @Column(name = "email")
    private String email; // Адрес электронной почты пользователя.
    @Column(name = "password_hash")
    private String password_hash; // Хеш пароля пользователя.
    @Column(name = "role")
    private String role; // Роль пользователя.
    @Column(name = "created_at")
    private Timestamp created_at; // Дата и время создания записи пользователя.
    @Column(name = "updated_at")
    private Timestamp updated_at; // Дата и время последнего обновления записи пользователя.

    /**
     * Конструктор без параметров.
     * Инициализирует поля объекта значениями по умолчанию.
     */
    public User() {
        this.username = "";
        this.email = "";
        this.password_hash = "";
        this.role = "customer";
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Конструктор с параметрами.
     * Инициализирует поля объекта переданными значениями.
     *
     * @param username      - имя пользователя.
     * @param email         - адрес электронной почты пользователя.
     * @param password_hash - хеш пароля пользователя.
     * @param role          - роль пользователя.
     */
    public User(String username, String email, String password_hash, String role) {
        this.username = username;
        this.email = email;
        this.password_hash = password_hash;
        this.role = role;
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.updated_at = (Timestamp) created_at.clone();
        Logger.getLogger("com.example.hwww.model.User").log(Level.INFO, "Initialized " + this.toString());
    }

    /**
     * Метод для получения идентификатора пользователя.
     *
     * @return - идентификатор пользователя.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Метод для получения имени пользователя.
     *
     * @return - имя пользователя.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод для получения адреса электронной почты пользователя.
     *
     * @return - адрес электронной почты пользователя.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Метод для получения хеша пароля пользователя.
     *
     * @return - хеш пароля пользователя.
     */
    public String getPassword_hash() {
        return password_hash;
    }

    /**
     * Метод для получения роли пользователя.
     *
     * @return - роль пользователя.
     */
    public String getRole() {
        return role;
    }

    /**
     * Метод для получения даты и времени создания записи пользователя.
     *
     * @return - дата и время создания записи пользователя.
     */
    public Timestamp getCreated_at() {
        return created_at;
    }

    /**
     * Метод для получения даты и времени последнего обновления записи пользователя.
     *
     * @return - дата и время последнего обновления записи пользователя.
     */
    public Timestamp getUpdated_at() {
        return updated_at;
    }

    /**
     * Метод для установки имени пользователя.
     *
     * @param username - имя пользователя.
     */
    public void setUsername(String username) {
        this.username = username;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Метод для установки адреса электронной почты пользователя.
     *
     * @param email - адрес электронной почты пользователя.
     */
    public void setEmail(String email) {
        this.email = email;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Метод для установки хеша пароля пользователя.
     *
     * @param password_hash - хеш пароля пользователя.
     */
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Метод для установки роли пользователя.
     *
     * @param role - роль пользователя.
     */
    public void setRole(String role) {
        this.role = role;
        this.updated_at = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Переопределение метода toString().
     * @return Строка с информацией о пользователе.
     */
    @Override
    public String toString() {
        return "User: name: " + username + "; email: " + email
                + "; role: " + role + "; created at: " + created_at.toString()
                + "; updated_at: " + updated_at.toString();
    }
}
