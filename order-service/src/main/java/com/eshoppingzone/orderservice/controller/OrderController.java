package com.eshoppingzone.orderservice.controller;

import com.eshoppingzone.orderservice.models.Order;
import com.eshoppingzone.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add-order")
    public ResponseEntity<?> addOrder(@RequestBody Order order){
        try{
            this.orderService.addOrder(order);
            return new ResponseEntity<>("Order Success",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-order-by-user-id")
    public ResponseEntity<?> getOrderByUserId(@RequestParam String userId){
        try{
            List<Order> order = this.orderService.findByUserId(userId);
            return  new ResponseEntity<>(order,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-order-by-agent-id")
    public ResponseEntity<?> getOrderByDeliveryAgentId(@RequestParam String deliveryAgentId){
        try{
            List<Order> order = this.orderService.findByDeliveryAgentId(deliveryAgentId);
            return  new ResponseEntity<>(order,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-order-by-not-assigned-delivery-agent")
    public ResponseEntity<?> getOrderByDeliveryAgentNotAssigned(){
        try{
            List<Order> order = this.orderService.findByDeliveryAgentAssigned(false);
            return  new ResponseEntity<>(order,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update-order")
    public ResponseEntity<?> updateOrder(@RequestBody Order order){
        try{
            this.orderService.addOrder(order);
            return new ResponseEntity<>(order,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
