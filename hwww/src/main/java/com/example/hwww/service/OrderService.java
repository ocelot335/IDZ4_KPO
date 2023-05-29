package com.example.hwww.service;

import com.example.hwww.model.Dish;
import com.example.hwww.model.Order;
import com.example.hwww.model.OrderDish;
import com.example.hwww.repository.*;
import com.example.hwww.request.OrderDishRequest;
import org.aspectj.weaver.ast.Or;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DishRepository dishRepository;

    @Autowired
    OrderDishRepository orderDishRepository;

    public int createOrder(String JWT, List<OrderDishRequest> dishes, String specialText) {
        int jwtAfterParse = new JWTService().validJWT(JWT);
        if (jwtAfterParse < 0) {
            return jwtAfterParse;
        }

        Order newOrder = new Order(jwtAfterParse, "В ожидании", specialText);
        orderRepository.save(newOrder);

        int orderId = newOrder.getId();
        for (OrderDishRequest dish :
                dishes) {
            if (createOrderDish(dish, orderId) == 1) {
                return -3;
            }
        }

        return 0;
    }

    private int createOrderDish(OrderDishRequest orderDishRequest, int orderId) {
        Optional<Dish> dish = dishRepository.findById(orderDishRequest.getDishId());
        if (dish.isEmpty()) {
            return 1;
        }
        OrderDish newOrderDish = new OrderDish(orderId, orderDishRequest.getDishId(),
                orderDishRequest.getQuantity(), dish.get().getPrice());
        orderDishRepository.save(newOrderDish);
        return 0;
    }
}
