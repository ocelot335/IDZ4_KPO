package com.example.hwww.service;

import com.example.hwww.model.Dish;
import com.example.hwww.model.User;
import com.example.hwww.repository.DishRepository;
import com.example.hwww.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;

    @Autowired
    UserRepository userRepository;

    public int createDish(String JWT, String name, String description, double price, int quantity) {
        int clientId = new JWTService().validJWT(JWT);
        if (clientId < 0) {
            return clientId;
        }
        Optional<User> user = userRepository.findById(clientId);
        if(!"manager".equals(user.get().getRole())) {
            return -3;
        }
        Dish dish = new Dish(name, description, quantity, price);
        dishRepository.save(dish);
        return 0;
    }
}
