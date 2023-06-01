/**
 * Репозиторий, который расширяет JpaRepository для работы с базой данных.
 * Он содержит информацию о блюдах, входящих в заказы.
 */
package com.example.hwww.repository;
import com.example.hwww.model.Order;
import com.example.hwww.model.OrderDish;
import com.example.hwww.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
}