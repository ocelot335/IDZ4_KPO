/**
 * Класс, представляющий сущность сессии в приложении.
 */
package com.example.hwww.model;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "session_token")
    private String token;
    @Column(name = "expires_at")
    private Timestamp expiresAt;
    /**
     * Конструктор по умолчанию, инициализирующий поля класса значениями по умолчанию.
     */
    public Session() {
        this.userId = -1;
        this.token = "";
        this.expiresAt = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Конструктор, инициализирующий поля класса заданными значениями.
     * @param userId идентификатор пользователя
     * @param token токен сессии
     * @param expiresAt время истечения срока действия сессии
     */
    public Session(int userId, String token, Date expiresAt) {
        this.userId = userId;
        this.token = token;
        this.expiresAt = new Timestamp(expiresAt.getTime());
        Logger.getLogger("com.example.hwww.model.Session").log(Level.INFO,
                "Session started: userId: " + userId
                        + "; expires at: " + expiresAt.toString());
    }
    /**
     * Метод, возвращающий идентификатор сессии.
     * @return идентификатор сессии
     */
    public Integer getId() {
        return id;
    }
    /**
     * Метод, возвращающий идентификатор пользователя, связанного с сессией.
     * @return идентификатор пользователя
     */
    public int getUserId() {
        return userId;
    }
    /**
     * Метод, возвращающий токен сессии.
     * @return токен сессии
     */
    public String getToken() {
        return token;
    }
    /**
     * Метод, возвращающий время истечения срока действия сессии.
     * @return время истечения срока действия сессии
     */
    public Timestamp getExpiresAt() {
        return expiresAt;
    }
    /**
     * Метод, устанавливающий время истечения срока действия сессии.
     * @param expiresAt время истечения срока действия сессии
     */
    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }
    /**
     * Метод, устанавливающий токен сессии.
     * @param token токен сессии
     */
    public void setToken(String token) {
        this.token = token;
    }
    /**
     * Метод, устанавливающий идентификатор пользователя, связанного с сессией.
     * @param userId идентификатор пользователя
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}