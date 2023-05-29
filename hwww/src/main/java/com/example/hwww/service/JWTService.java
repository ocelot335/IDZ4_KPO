package com.example.hwww.service;

import com.example.hwww.model.Session;
import com.example.hwww.repository.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {
    @Autowired
    SessionRepository sessionRepository;
    private static final long EXPIRATION_TIME = 600_000; // 10 minutes
    private static final String SECRET = "amogus";

    public static String getJWT(String str) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(str)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public int validJWT(String token) {
        Optional<Session> session = sessionRepository.findByToken(token);
        if (session.isEmpty()) {
            return -1;
        }
        if (session.get().getExpiresAt().before(new Date())) {
            return -2;
        }
        return session.get().getUserId();
    }
}
