package com.eshoppingzone.orderservice.service;

import com.eshoppingzone.orderservice.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void  addOrder(Order order);
    List<Order> findByDeliveryAgentAssigned(boolean deliveryAgentAssigned);
    List<Order> findByDeliveryAgentId(String deliveryAgentId);
    List<Order> findByUserId(String userId);
}
