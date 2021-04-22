package com.eshoppingzone.orderservice.components;

import com.eshoppingzone.orderservice.models.Order;
import com.eshoppingzone.orderservice.models.OrderMessage;
import com.eshoppingzone.orderservice.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {


    @Autowired
    OrderService orderService;

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    @RabbitListener(queues = "checkout_queue")
    public void getOrderFromQueue(OrderMessage orderMessage){
        Order order =  new Order();
        order.setUserId(orderMessage.getUserId());
        order.setTotalPrice(orderMessage.getTotalPrice());
        order.setTotalSavingsAmount(orderMessage.getTotalSavingsAmount());
        order.setItems(orderMessage.getItems());
        order.setAddress(orderMessage.getAddress());
        order.setMobileNumber(orderMessage.getMobileNumber());
        order.setEmail(orderMessage.getEmail());
        this.orderService.addOrder(order);
    }
}
