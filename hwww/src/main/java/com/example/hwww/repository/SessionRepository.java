package com.example.hwww.repository;

import com.example.hwww.model.Session;
import com.example.hwww.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SessionRepository extends JpaRepository<Session, Integer> {
    Optional<Session> findByToken(String token);
    Optional<Session> findByUserId(int userId);
}