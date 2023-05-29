package com.example.hwww.repository;

import com.example.hwww.model.Dish;
import com.example.hwww.model.Session;
import com.example.hwww.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    Optional<Dish> findById(int id);
}
