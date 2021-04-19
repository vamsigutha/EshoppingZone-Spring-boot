package com.eshoppingzone.cartservice.cart.models;

import lombok.Data;

@Data
public class Item {
    private String productId;
    private String title;
    private String image;
    private Price price;
    private int quantity;
}
