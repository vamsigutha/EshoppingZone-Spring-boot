package com.eshoppingzone.productservice.Products.Models;

import lombok.Data;

@Data
public class Price {
    private String symbol;
    private String currency;
    private double current_price;
    private Boolean discounted;
    private double before_price;
    private double savings_amount;
    private double savings_percent;
}
