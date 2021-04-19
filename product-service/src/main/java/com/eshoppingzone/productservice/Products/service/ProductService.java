package com.eshoppingzone.productservice.Products.service;

import com.eshoppingzone.productservice.Products.Models.Product;
import com.eshoppingzone.productservice.Products.Models.ProductThumbnail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface ProductService {

    //customer
    Page<Product> getAllProducts(Pageable pageable);
    Page<ProductThumbnail> getProductByTitle(String title, Pageable pageable);
    Optional<Product> getProductById(String id);


    Page<ProductThumbnail> getProductsByCategory(String category, String sub_category, Pageable pageable);

    //merchant
    void addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProductById(String id);
    Page<Product> getProductByMerchantId(String merchantId, Pageable pageable);

}
