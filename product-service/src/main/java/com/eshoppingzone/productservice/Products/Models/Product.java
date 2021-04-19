package com.eshoppingzone.productservice.Products.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    @Field("merchant_id")
    private String merchantId;
    @TextIndexed(weight=3)
    private String title;
    private String description;
    private List<String> feature_bullets;
    private Review reviews;
    private Boolean item_available;
    private Price price;
    private int total_images;
    private List<String> images;
    private String delivery_message;
    private ProductInfo product_information;
    private Category category;

}
