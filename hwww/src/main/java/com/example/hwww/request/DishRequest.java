package com.example.hwww.request;

import lombok.Data;

@Data
public class DishRequest {
    String JWT;
    String name;
    String description;
    double price;
    int quantity;
}
