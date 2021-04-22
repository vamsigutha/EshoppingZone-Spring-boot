package com.eshoppingzone.cartservice.cart.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private String userId;
    private double totalPrice;
    private double totalSavingsAmount;
    private List<Item> items = new ArrayList<>();
    private Address address;
    private long mobileNumber;
    private String email;
}
