package com.eshoppingzone.orderservice.service;

import com.eshoppingzone.orderservice.models.Order;
import com.eshoppingzone.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addOrder(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public List<Order> findByDeliveryAgentAssigned(boolean deliveryAgentAssigned) {
        return this.orderRepository.findByDeliveryAgentAssigned(deliveryAgentAssigned);
    }

    @Override
    public List<Order> findByDeliveryAgentId(String deliveryAgentId) {
        return this.orderRepository.findByDeliveryAgentId(deliveryAgentId);
    }

    @Override
    public List<Order> findByUserId(String userId) {
        return this.orderRepository.findByUserId(userId);
    }
}
