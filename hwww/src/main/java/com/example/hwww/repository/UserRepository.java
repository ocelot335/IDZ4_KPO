/**
 * Репозиторий пользователей, который расширяет JpaRepository для работы с базой данных.
 * Он содержит методы для поиска пользователей по электронной почте, идентификатору и имени пользователя.
 */
package com.example.hwww.repository;
import com.example.hwww.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Метод для поиска пользователя по электронной почте.
     * @param email Электронная почта пользователя.
     * @return Optional объект, содержащий пользователя с заданной электронной почтой, если он существует.
     */
    Optional<User> findByEmail(String email);
    /**
     * Метод для поиска пользователя по идентификатору.
     * @param id Идентификатор пользователя.
     * @return Optional объект, содержащий пользователя с заданным идентификатором, если он существует.
     */
    Optional<User> findById(int id);
    /**
     * Метод для поиска пользователя по имени пользователя.
     * @param username Имя пользователя.
     * @return Optional объект, содержащий пользователя с заданным именем пользователя, если он существует.
     */
    Optional<User> findByUsername(String username);
}