package com.eshoppingzone.paymentservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Wallet {
    @Id
    private String walletId;
    private String userId;
    private double totalAmount;
}
