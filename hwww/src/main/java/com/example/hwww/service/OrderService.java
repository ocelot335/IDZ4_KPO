/**
 * Сервис для работы с заказами.
 */
package com.example.hwww.service;
import com.example.hwww.exception.JWTException;
import com.example.hwww.exception.RequestException;
import com.example.hwww.exception.RoleException;
import com.example.hwww.model.Dish;
import com.example.hwww.model.Order;
import com.example.hwww.model.OrderDish;
import com.example.hwww.model.User;
import com.example.hwww.repository.*;
import com.example.hwww.request.order.OrderDishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {
    private final int maxLengthOfSpecialText = 1000;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    OrderDishRepository orderDishRepository;
    @Autowired
    JWTService jwtService;
    @Autowired
    CookingService cookingService;
    /**
     * Создание заказа с указанными блюдами и специальным текстом.
     *
     * @param JWT         токен пользователя
     * @param dishes      список блюд в заказе
     * @param specialText специальный текст для заказа
     * @return идентификатор созданного заказа
     * @throws JWTException     если возникла ошибка с JWT
     * @throws RequestException если возникла ошибка с запросом
     */
    public int createOrder(String JWT, List<OrderDishRequest> dishes, String specialText) throws JWTException, RequestException {
        int usersId = jwtService.checkJWTAndGiveUsersId(JWT);
        if (specialText.length() > maxLengthOfSpecialText) {
            throw new RequestException("Слишком большое специальное сообщение: "
                    + specialText.length() + " символов(максимум " + maxLengthOfSpecialText + ")");
        }
        Order newOrder = new Order(usersId, "В ожидании", specialText);
        orderRepository.save(newOrder);
        int orderId = newOrder.getId();
        try {
            for (OrderDishRequest dish : dishes) {
                createOrderDish(dish, orderId);
            }
        } catch (RequestException e) {
            newOrder.setStatus("Отменён");
            orderRepository.save(newOrder);
            throw e;
        }
        cookingService.cookOrder(newOrder);
        return orderId;
    }
    /**
     * Создание экземпляра OrderDish на основе указанного заказа и блюда.
     *
     * @param orderDishRequest данные для создания OrderDish
     * @param orderId          идентификатор заказа
     * @throws RequestException если возникла ошибка с запросом
     */
    private void createOrderDish(OrderDishRequest orderDishRequest, int orderId) throws RequestException {
        Optional<Dish> maybeDish = dishRepository.findById(orderDishRequest.getDishId());
        if (maybeDish.isEmpty()) {
            throw new RequestException("Нет блюда с таким идентификатором(" + orderId + ")");
        }
        Dish dish = maybeDish.get();
        if (!dish.getAvailable()) {
            throw new RequestException("Блюдо c таким идентификатором(" + dish.getId() + ") недоступно");
        }
        if (dish.getQuantity() - orderDishRequest.getQuantity() < 0) {
            throw new RequestException("Невозможно получить требуемое количество(" + orderDishRequest.getQuantity() + ") блюда." +
                    " Максимум: " + dish.getQuantity());
        }
        dish.setQuantity(dish.getQuantity() - orderDishRequest.getQuantity());
        dishRepository.save(dish);
        OrderDish newOrderDish = new OrderDish(orderId, orderDishRequest.getDishId(),
                orderDishRequest.getQuantity(), dish.getPrice());
        orderDishRepository.save(newOrderDish);
    }
    /**
     * Получение информации о заказе по его идентификатору.
     *
     * @param JWT    токен пользователя
     * @param orderId идентификатор заказа
     * @return строка с информацией о заказе
     * @throws JWTException     если возникла ошибка с JWT
     * @throws RequestException если возникла ошибка с запросом
     * @throws RoleException    если пользователь не имеет прав на просмотр информации
     */
    public String getInfo(String JWT, int orderId) throws JWTException, RequestException, RoleException {
        Optional<Order> maybeOrder = orderRepository.findById(orderId);
        int id = jwtService.checkJWTAndGiveUsersId(JWT);
        if (maybeOrder.isEmpty()) {
            throw new RequestException("Нет заказа с таким номером");
        }
        Optional<User> maybeUser = userRepository.findById(id);
        if (maybeUser.get().getId() != maybeOrder.get().getUserId()
                && !"manager".equals(maybeUser.get().getRole())) {
            throw new RoleException("Нет прав к просмотру этой информации");
        }
        return "Статус заказа: " + maybeOrder.get().getStatus();
    }
    /**
     * Получение меню с доступными блюдами.
     *
     * @return строка с меню
     */
    public String getMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Меню:\n");
        List<Dish> dishes = dishRepository.findAll();
        for (Dish dish :
                dishes) {
            if (dish.getAvailable() && dish.getQuantity() > 0) {
                menu.append("Id: " + dish.getId() + "\nНазвание: " + dish.getName()
                        + "\nОписание: " + dish.getDescription()
                        + "\nЦена: " + dish.getPrice() + "\n\n");
            }
        }
        return menu.toString();
    }
}