package com.eshoppingzone.productservice.Products.Models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Review {
    private String total_reviews;
    private Map<String,String> stars_stat;
    private List<ReviewDetail> result;
}
