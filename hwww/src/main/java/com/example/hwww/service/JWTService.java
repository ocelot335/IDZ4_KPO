/**
 * Сервис для работы с JWT-токенами.
 */
package com.example.hwww.service;
import com.example.hwww.exception.JWTException;
import com.example.hwww.model.Session;
import com.example.hwww.repository.SessionRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
@Service
public class JWTService {
    @Autowired
    SessionRepository sessionRepository;
    private static final long EXPIRATION_TIME = 600_000; // Время жизни токена - 10 минут
    private static final String SECRET = "amogus"; // Секретный ключ для подписи токена
    /**
     * Генерация JWT-токена на основе переданной строки.
     *
     * @param str строка, на основе которой будет сгенерирован токен
     * @return строка с сгенерированным токеном
     */
    public String getJWT(String str) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(str)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    /**
     * Получение даты истечения срока действия токена.
     *
     * @param token токен, для которого нужно получить дату истечения срока действия
     * @return дата истечения срока действия токена
     */
    public Date getExpiration(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getExpiration();
    }
    /**
     * Проверка токена и возвращение идентификатора пользователя, связанного с этим токеном.
     *
     * @param token токен, который нужно проверить
     * @return идентификатор пользователя, связанного с токеном
     * @throws JWTException если токен не найден или его срок действия истек
     */
    public int checkJWTAndGiveUsersId(String token) throws JWTException {
        Optional<Session> session = sessionRepository.findByToken(token);
        if (session.isEmpty()) {
            throw new JWTException("Нету пользователя с таким токеном");
        }
        if (session.get().getExpiresAt().before(new Date())) {
            sessionRepository.delete(session.get());
            throw new JWTException("Сессия истекла");
        }
        return session.get().getUserId();
    }
}