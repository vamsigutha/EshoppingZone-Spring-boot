package com.eshoppingzone.cartservice.cart.models;

import lombok.Data;

@Data
public class Address {
    private String houseNumber;
    private String streetName;
    private String colonyName;
    private String city;
    private String state;
    private int pincode;
}
