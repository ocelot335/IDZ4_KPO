package com.example.hwww.request;

import com.example.hwww.model.Dish;
import com.example.hwww.model.Order;
import com.example.hwww.model.OrderDish;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    String JWT;
    List<OrderDishRequest> dishes;
    String specialText;
}
