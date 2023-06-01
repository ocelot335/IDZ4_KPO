/**
 * Репозиторий сеансов, который расширяет JpaRepository для работы с базой данных.
 * Он содержит методы для поиска сеансов по токену и идентификатору пользователя.
 */
package com.example.hwww.repository;
import com.example.hwww.model.Session;
import com.example.hwww.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SessionRepository extends JpaRepository<Session, Integer> {
    /**
     * Метод для поиска сеанса по токену.
     * @param token Токен сеанса.
     * @return Optional объект, содержащий сеанс с заданным токеном, если он существует.
     */
    Optional<Session> findByToken(String token);
    /**
     * Метод для поиска сеанса по идентификатору пользователя.
     * @param userId Идентификатор пользователя.
     * @return Optional объект, содержащий сеанс пользователя с заданным идентификатором, если он существует.
     */
    Optional<Session> findByUserId(int userId);
}