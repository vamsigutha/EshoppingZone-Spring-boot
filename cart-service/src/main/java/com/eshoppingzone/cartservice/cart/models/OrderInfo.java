package com.eshoppingzone.cartservice.cart.models;

import lombok.Data;

@Data
public class OrderInfo {
    private String token;
    private String userId;
    private Address address;
    private long mobileNumber;
    private String email;
}
