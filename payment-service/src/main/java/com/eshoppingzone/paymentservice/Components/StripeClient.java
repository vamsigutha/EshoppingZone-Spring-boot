package com.eshoppingzone.paymentservice.Components;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {
    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51GmkC1HGowBhoO3LcaBY8v8Y8UIXdKvgmfmgeD23UtBRDl9XP7hjVRrLoYET2HaqFCP0RDy0Wseqxnu7ulFlnYu200G3u4an1D";
    }

    public Charge chargeCreditCard(String token, int amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", amount*100);
        chargeParams.put("currency", "INR");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
