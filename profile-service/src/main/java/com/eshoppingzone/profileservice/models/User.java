package com.eshoppingzone.profileservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;

    private String username;

    private String image;

    private String email;

    private long mobileNumber;

    private String dateOfBirth;

    private String gender;


    private String password;

    private Set<Role> roles = new HashSet<>();

    private List<Address> addressList = new ArrayList<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.image="";
        this.mobileNumber = 0;
        this.dateOfBirth = "";
    }


    public User() {

    }
}
