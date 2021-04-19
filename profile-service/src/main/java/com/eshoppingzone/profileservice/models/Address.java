package com.eshoppingzone.profileservice.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "address")
@Data
public class Address {
    private String houseNumber;
    private String streetName;
    private String colonyName;
    private String city;
    private String state;
    private int pincode;

}
