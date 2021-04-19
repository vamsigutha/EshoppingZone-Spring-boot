package com.eshoppingzone.orderservice.repository;

import com.eshoppingzone.orderservice.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByDeliveryAgentAssigned(boolean deliveryAgentAssigned);
    List<Order> findByDeliveryAgentId(String deliveryAgentId);
    List<Order>findByUserId(String userId);

}
