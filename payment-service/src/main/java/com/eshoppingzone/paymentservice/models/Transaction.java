package com.eshoppingzone.paymentservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Transaction {

    @Id
    private String transactionId;
    private String userId;
    private List<String> productIdList;
    private String amount;
}
