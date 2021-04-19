package com.eshoppingzone.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p-> p.path("/api/v1/products/**")
                    .uri("lb://products-service"))
                .route(p->p.path("/api/v1/auth/**")
                    .uri("lb://profile-service"))
                .route(p->p.path("/api/v1/cart/**")
                    .uri("lb://cart-service"))
                .route(p->p.path("/api/v1/payment/**")
                        .uri("lb://payment-service"))
                .build();
    }
}
