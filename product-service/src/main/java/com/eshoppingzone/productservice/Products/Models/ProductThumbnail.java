package com.eshoppingzone.productservice.Products.Models;

import lombok.Data;


import java.util.List;

@Data
public class ProductThumbnail {

    private String id;

    private String title;

    private Price price;

    private List<String> images;

    private Category category;
}
