package com.example.hwww.repository;

import com.example.hwww.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String username);
}