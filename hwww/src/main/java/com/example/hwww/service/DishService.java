/**
 * Сервис для работы с блюдами, который предоставляет методы для создания, чтения, обновления и удаления блюд.
 */
package com.example.hwww.service;
import com.example.hwww.exception.JWTException;
import com.example.hwww.exception.RequestException;
import com.example.hwww.exception.RoleException;
import com.example.hwww.model.Dish;
import com.example.hwww.model.User;
import com.example.hwww.repository.DishRepository;
import com.example.hwww.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JWTService jwtService;
    /**
     * Метод для создания нового блюда.
     *
     * @param JWT         Токен пользователя.
     * @param name        Название блюда.
     * @param description Описание блюда.
     * @param price       Цена блюда.
     * @param quantity    Количество порций.
     * @param isAvailable Доступность блюда.
     * @throws JWTException Если возникает ошибка при проверке токена.
     * @throws RoleException Если пользователь не имеет прав на создание блюда.
     */
    public void createDish(String JWT, String name, String description,
                           double price, int quantity, boolean isAvailable) throws JWTException, RoleException {
        int clientId = jwtService.checkJWTAndGiveUsersId(JWT);
        Optional<User> user = userRepository.findById(clientId);
        if (!"manager".equals(user.get().getRole())) {
            throw new RoleException("Только менеджер может создавать новые блюда");
        }
        Dish dish = new Dish(name, description, quantity, price, isAvailable);
        dishRepository.save(dish);
    }
    /**
     * Метод для получения информации о блюде.
     *
     * @param JWT    Токен пользователя.
     * @param dishId Идентификатор блюда.
     * @return JSON-строка с информацией о блюде.
     * @throws JWTException Если возникает ошибка при проверке токена.
     * @throws RequestException Если блюда с указанным идентификатором не существует.
     * @throws RoleException Если пользователь не имеет прав на получение информации о блюде.
     */
    public String readDish(String JWT, int dishId) throws JWTException, RequestException, RoleException {
        int clientId = jwtService.checkJWTAndGiveUsersId(JWT);
        User user = userRepository.findById(clientId).get();
        if (!"manager".equals(user.getRole()) && !"chef".equals(user.getRole())) {
            throw new RoleException("Только менеджер или шеф может получать информацию о блюде");
        }
        Dish dish = checkDish(dishId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(dish);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "Internal serialization error";
    }
    /**
     * Метод для обновления информации о блюде.
     *
     * @param JWT         Токен пользователя.
     * @param dishId      Идентификатор блюда.
     * @param name        Новое название блюда.
     * @param description Новое описание блюда.
     * @param price       Новая цена блюда.
     * @param quantity    Новое количество порций.
     * @param isAvailable Новая доступность блюда.
     * @throws JWTException Если возникает ошибка при проверке токена.
     * @throws RequestException Если блюда с указанным идентификатором не существует.
     * @throws RoleException Если пользователь не имеет прав на обновление информации о блюде.
     */
    public void updateDish(String JWT, int dishId, String name, String description,
                           double price, int quantity, boolean isAvailable) throws JWTException, RequestException, RoleException {
        int clientId = jwtService.checkJWTAndGiveUsersId(JWT);
        User user = userRepository.findById(clientId).get();
        if (!"manager".equals(user.getRole())) {
            throw new RoleException("Только менеджер может изменять блюда");
        }
        Dish dish = checkDish(dishId);
        dish.setName(name);
        dish.setDescription(description);
        dish.setPrice(price);
        dish.setQuantity(quantity);
        dish.setAvailable(isAvailable);
        dishRepository.save(dish);
    }
    /**
     * Метод для удаления блюда.
     *
     * @param JWT    Токен пользователя.
     * @param dishId Идентификатор блюда.
     * @throws JWTException Если возникает ошибка при проверке токена.
     * @throws RequestException Если блюда с указанным идентификатором не существует.
     * @throws RoleException Если пользователь не имеет прав на удаление блюда.
     */
    public void deleteDish(String JWT, int dishId) throws JWTException, RequestException, RoleException {
        int clientId = jwtService.checkJWTAndGiveUsersId(JWT);
        User user = userRepository.findById(clientId).get();
        if (!"manager".equals(user.getRole())) {
            throw new RoleException("Только менеджер может изменять блюда");
        }
        Dish dish = checkDish(dishId);
        dishRepository.delete(dish);
    }
    /**
     * Вспомогательный метод для проверки существования блюда с указанным идентификатором.
     *
     * @param dishId Идентификатор блюда.
     * @return Объект Dish, если блюдо с указанным идентификатором существует.
     * @throws RequestException Если блюда с указанным идентификатором не существует.
     */
    private Dish checkDish(int dishId) throws RequestException {
        Optional<Dish> dish = dishRepository.findById(dishId);
        if (dish.isEmpty()) {
            throw new RequestException("Не существует такого блюда");
        }
        return dish.get();
    }
}