package com.example.hwww.service;

import com.example.hwww.model.Session;
import com.example.hwww.model.User;
import com.example.hwww.repository.SessionRepository;
import com.example.hwww.repository.UserRepository;
import com.mysql.cj.conf.ConnectionUrlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.hwww.service.JWTService.getJWT;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    public int register(String username, String email, String password) {

        if (email == null || !email.contains("@") || email.length() >= 100) {
            return 1;
        }
        if (username == null || username.length() >= 50) {
            return 2;
        }
        if (password.length() <= 5) {
            return 3;
        }
        if (userRepository.findByUsername(username).isPresent()) {
            return 4;
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return 5;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return -1;
        }
        messageDigest.update(password.getBytes());
        String stringHash = new String(messageDigest.digest());
        userRepository.save(new User(username, email, stringHash, "customer"));
        return 0;
    }

    public int giveRole(String email, String role) {
        if (!("chef".equals(role) || "manager".equals(role) || "customer".equals(role))) {
            return 1;
        }
        if (email == null || !email.contains("@")) {
            return 2;
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return 3;
        }
        try {
            user.get().setRole(role);
            userRepository.save(user.get());
        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

    public ConnectionUrlParser.Pair<Integer, String> login(String email, String password) {
        if (email == null || !email.contains("@")) {
            return new ConnectionUrlParser.Pair<>(2, "");
        }
        if (password.length() <= 5) {
            return new ConnectionUrlParser.Pair<>(3, "");
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return new ConnectionUrlParser.Pair<>(4, "");
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return new ConnectionUrlParser.Pair<>(-1, "");
        }
        messageDigest.update(password.getBytes());
        String stringHash = new String(messageDigest.digest());
        String token;
        if (user.get().getPassword_hash().equals(stringHash)) {
            token = JWTService.getJWT(email);
            Session newSession = new Session(user.get().getId(), token);
            sessionRepository.save(newSession);
        } else {
            return new ConnectionUrlParser.Pair<>(1, "");
        }
        return new ConnectionUrlParser.Pair<>(0, token);
    }
}
