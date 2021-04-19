package com.eshoppingzone.productservice.Products.controller;

import com.eshoppingzone.productservice.Products.Models.Product;
import com.eshoppingzone.productservice.Products.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/products")
@Api(value = "Merchant Product Controller to handle all products related actions and queries ")
public class MerchantProductController {
    ProductService productService;

    @Autowired
    public MerchantProductController(ProductService productService) {
        this.productService = productService;
    }

    //Get Products By Merchant Id

    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping("/merchant/{id}")
    public ResponseEntity<?> getProductsByMerchantId(@PathVariable String id, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "3") int size) {
        try {
            List<Product> products = new ArrayList<>();

            Pageable paging = PageRequest.of(page, size);

            Page<Product> pageProducts = this.productService.getProductByMerchantId(id, paging);

            return getMapResponseEntity(pageProducts);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Add Product

    @PreAuthorize("hasRole('MERCHANT')")
    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            this.productService.addProduct(product);
            return new ResponseEntity<>("Product added", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //Update Product

    @PreAuthorize("hasRole('MERCHANT')")
    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            this.productService.updateProduct(product);
            return new ResponseEntity<>("Product updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete Product By Id

    @PreAuthorize("hasRole('MERCHANT')")
    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            this.productService.deleteProductById(id);
            return new ResponseEntity<>("Product deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Common Method for constructing response from result of products page

    private ResponseEntity<Map<String, Object>> getMapResponseEntity(Page<Product> pageProducts) {
        List<Product> products;
        products = pageProducts.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageProducts.getNumber());
        response.put("totalItems", pageProducts.getTotalElements());
        response.put("totalPages", pageProducts.getTotalPages());
        response.put("currentPageItems", pageProducts.getSize());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
