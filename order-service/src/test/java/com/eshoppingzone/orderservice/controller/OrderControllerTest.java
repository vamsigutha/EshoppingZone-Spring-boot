package com.eshoppingzone.orderservice.controller;

import com.eshoppingzone.orderservice.auth.filter.JwtAuthFilter;
import com.eshoppingzone.orderservice.auth.security.JwtAuthenticationEntryPoint;
import com.eshoppingzone.orderservice.auth.security.JwtAuthenticationProvider;
import com.eshoppingzone.orderservice.auth.security.SecurityConfig;
import com.eshoppingzone.orderservice.models.Order;
import com.eshoppingzone.orderservice.repository.OrderRepository;
import com.eshoppingzone.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    OrderService orderService;

    @MockBean
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @MockBean
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @MockBean
    OrderRepository orderRepository;



    @BeforeEach
    void setUp() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
    }

    @Test
    void getOrderByDeliveryAgentId() throws Exception {
        List<Order> orders;
        Order order   =  new Order();
        order.setOrderId("dfjdjkj");
        order.setDeliveryAgentAssigned(false);
        orders = Collections.singletonList(order);

        when(orderService.findByDeliveryAgentId(any())).thenReturn(orders);

        String url = "/get-order-by-agent-id";

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(url)
                .param("deliveryAgentId","dfjdjkj")
                .with(csrf())

                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andReturn();

        String jsonResult = result.getResponse().getContentAsString();

        System.out.println("result"+jsonResult);
    }
}