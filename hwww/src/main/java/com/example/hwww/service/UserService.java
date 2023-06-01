package com.example.hwww.service;
import com.example.hwww.exception.JWTException;
import com.example.hwww.exception.RequestException;
import com.example.hwww.exception.RoleException;
import com.example.hwww.model.Session;
import com.example.hwww.model.User;
import com.example.hwww.repository.SessionRepository;
import com.example.hwww.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {
    private final String secretKey = "secret";
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    JWTService jwtService;
    /**
     * Регистрация нового пользователя.
     *
     * @param username Имя пользователя
     * @param email    Email пользователя
     * @param password Пароль пользователя
     * @throws RequestException Исключение, если введены неправильные данные
     */
    public void register(String username, String email, String password) throws RequestException {
        if (email == null || !email.contains("@") || email.length() >= 100) {
            throw new RequestException("Введён email неправильного формата");
        }
        if (username == null || username.length() >= 50) {
            throw new RequestException("Имя пользователя в неправильном формате");
        }
        if (password.length() <= 5) {
            throw new RequestException("Слишком маленький пароль");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RequestException("Пользователь с таким именем уже есть");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RequestException("Пользователь с таким email уже существует");
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return;
        }
        messageDigest.update(password.getBytes());
        String stringHash = new String(messageDigest.digest());
        userRepository.save(new User(username, email, stringHash, "customer"));
    }
    /**
     * Назначение роли пользователю.
     *
     * @param email Email пользователя
     * @param role  Роль, которую надо назначить
     * @throws RoleException    Исключение, если роль некорректна
     * @throws RequestException Исключение, если пользователь не найден
     */
    public void giveRole(String email, String role, String key) throws RoleException, RequestException {
        if(!secretKey.equals(key)) {
            throw new RequestException("Неверный секретный ключ");
        }
        if (!("chef".equals(role) || "manager".equals(role) || "customer".equals(role))) {
            throw new RoleException("Нет такой роли");
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RequestException("Нет пользователя с таким email");
        }
        user.get().setRole(role);
        userRepository.save(user.get());
    }
    /**
     * Авторизация пользователя.
     *
     * @param email    Email пользователя
     * @param password Пароль пользователя
     * @return Токен для авторизации
     * @throws RequestException Исключение, если введены неправильные данные
     */
    public String login(String email, String password) throws RequestException {
        if (email == null || !email.contains("@")) {
            throw new RequestException("Неверный формат email");
        }
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RequestException("Нет пользователя с таким email");
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            //There is no error(ordinary)
        }
        messageDigest.update(password.getBytes());
        String stringHash = new String(messageDigest.digest());
        String token;
        if (user.get().getPassword_hash().equals(stringHash)) {
            token = jwtService.getJWT(email);
            Date expiresAt = jwtService.getExpiration(token);
            Session newSession = new Session(user.get().getId(), token, expiresAt);
            sessionRepository.save(newSession);
        } else {
            throw new RequestException("Пароли не совпадают");
        }
        return token;
    }
    /**
     * Получение информации о пользователе.
     *
     * @param JWT     Токен для авторизации
     * @param usersID ID пользователя
     * @return Информация о пользователе в виде строки
     * @throws JWTException  Исключение, если токен недействителен
     * @throws RoleException Исключение, если у пользователя нет прав на просмотр информации
     */
    public String getInfo(String JWT, int usersID) throws JWTException, RoleException, RequestException {
        int jwtAfterParse = jwtService.checkJWTAndGiveUsersId(JWT);
        Optional<User> user = userRepository.findById(jwtAfterParse);
        if (!"manager".equals(user.get().getRole())) {
            throw new RoleException("Получение информации о пользователе возможно только для менеджера");
        }
        Optional<User> neededUser = userRepository.findById(usersID);
        if(neededUser.isEmpty()) {
            throw new RequestException("Нет пользователя с таким идентификатором");
        }
        return neededUser.get().toString();
    }
    /**
     * Проверка пароля на корректность.
     *
     * @param password Пароль для проверки
     * @throws RequestException Исключение, если пароль некорректен
     */
    private void checkPassword(String password) throws RequestException {
        if (password == null || password.length() <= 5 || password.length() > 50) {
            throw new RequestException("Пароль неверного формата");
        }
    }
}