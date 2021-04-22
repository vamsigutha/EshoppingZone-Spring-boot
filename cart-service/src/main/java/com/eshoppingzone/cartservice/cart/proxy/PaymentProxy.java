package com.eshoppingzone.cartservice.cart.proxy;

import com.stripe.model.Charge;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payment-service")
public interface PaymentProxy {
    @PostMapping("/api/v1/payment/charge")
    String makePayment(@RequestParam String token, @RequestParam String amount);

}
