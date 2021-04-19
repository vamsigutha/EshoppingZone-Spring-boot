package com.eshoppingzone.orderservice.models;

import lombok.Data;

import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Order {
    @Id
    private String orderId;
    private String userId;
    private double totalPrice;
    private List<Item> items;
    private String deliveryAgentId;
    private boolean deliveryAgentAssigned=false;
    private String orderStatus="Received";
    private Address address;
    private long mobileNumber;
    private String email;
}
