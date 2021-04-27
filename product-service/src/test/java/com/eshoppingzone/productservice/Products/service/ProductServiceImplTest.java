package com.eshoppingzone.productservice.Products.service;

import com.eshoppingzone.productservice.Products.Models.Product;
import com.eshoppingzone.productservice.Products.Models.ProductThumbnail;
import com.eshoppingzone.productservice.Products.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductByTitle() {
        ProductThumbnail productThumbnail = new ProductThumbnail();
        productThumbnail.setTitle("abc");

        List<ProductThumbnail> productThumbnailList = new ArrayList<>();
        productThumbnailList.add(productThumbnail);

        Pageable paging = PageRequest.of(1, 1);

        Page<ProductThumbnail> pageProducts  = new PageImpl<ProductThumbnail>(productThumbnailList);

        Mockito.when(productRepository.findAllBy(any(),any())).thenReturn(pageProducts);

        Page<ProductThumbnail> products =  productService.getProductByTitle("abc",paging);

        assertNotNull(products);
        assertEquals(products.getTotalElements(),1);



    }

    @Test
    void getProductsByMerchantId(){

        Product product  = new Product();
        product.setId("123");
        product.setTitle("abc");
        product.setDelivery_message("not deliverable");
        product.setMerchantId("xyz");

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Pageable paging = PageRequest.of(1, 1);

        Page<Product> pageProducts  = new PageImpl<Product>(productList);

        Mockito.when(productRepository.findByMerchantId(any(),any())).thenReturn(pageProducts);

        Page<Product> resultProducts = productService.getProductByMerchantId("xyz",paging);
        assertNotNull(resultProducts);
        assertEquals(resultProducts.getTotalElements(),1);

    }
}