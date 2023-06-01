/**
 * Репозиторий блюд, который расширяет JpaRepository для работы с базой данных.
 * Он содержит методы для поиска блюд по идентификатору.
 */
package com.example.hwww.repository;
import com.example.hwww.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface DishRepository extends JpaRepository<Dish, Integer> {
    /**
     * Метод для поиска блюда по его идентификатору.
     * @param id Идентификатор блюда.
     * @return Optional объект, содержащий блюдо с заданным идентификатором, если оно существует.
     */
    Optional<Dish> findById(int id);
}