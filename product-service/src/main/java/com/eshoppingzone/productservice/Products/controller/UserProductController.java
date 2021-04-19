package com.eshoppingzone.productservice.Products.controller;

import com.eshoppingzone.productservice.Products.Models.Product;
import com.eshoppingzone.productservice.Products.Models.ProductThumbnail;
import com.eshoppingzone.productservice.Products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1/products")
@Api(value = "User Product Resource to handle all products related queries ")
public class UserProductController {

    ProductService productService;

    @Autowired
    public UserProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/product-id")
    public ResponseEntity<?> getProductById(@RequestParam String productId){
        try{
            Optional<Product> product = this.productService.getProductById(productId);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    //Get All Products, if title present get products related to that title

    @GetMapping("/")
    @ApiOperation(value = "Return all products available in the System with pagination", response = Map.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Requested Resource Not Found")
    })
    public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam(required = false) String title,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "20") int size) {
        try {

            List<ProductThumbnail> products = new ArrayList<>();

            Pageable paging = PageRequest.of(page, size);

            Page<ProductThumbnail> pageProducts;
//
//            if (title == null) {
//                pageProducts = this.productService.getAllProducts(paging);
//            } else {
//                pageProducts = this.productService.getProductByTitle(title, paging);
//            }

            pageProducts = this.productService.getProductByTitle(title, paging);

            return getMapResponseEntity(pageProducts);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //Get Products by Category and Sub Category

    @GetMapping("/category")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@RequestParam String category, @RequestParam String subCategory, @RequestParam int page,
                                                                     @RequestParam(defaultValue = "20") int size) {
        try {

            List<ProductThumbnail> products = new ArrayList<>();

            page = page-1;

            Pageable paging = PageRequest.of(page, size);

            Page<ProductThumbnail> pageProducts = this.productService.getProductsByCategory(category, subCategory, paging);


            return getMapResponseEntity(pageProducts);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Common Method for constructing response from result of products page

    private ResponseEntity<Map<String, Object>> getMapResponseEntity(Page<ProductThumbnail> pageProducts) {
        List<ProductThumbnail> products;
        products = pageProducts.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", pageProducts.getNumber()+1);
        response.put("totalItems", pageProducts.getTotalElements());
        response.put("totalPages", pageProducts.getTotalPages());
        response.put("currentPageItems", pageProducts.getSize());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
