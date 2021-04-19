package com.eshoppingzone.productservice.Products.Models;

import lombok.Data;

@Data
public class ReviewDetail {
    private String id;
    private String review_data;
    private String name;
    private int rating;
    private String title;
    private String review;
    private Boolean verified_purchase;

}
