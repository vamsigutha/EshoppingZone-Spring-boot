package com.eshoppingzone.productservice.Products.service;

import com.eshoppingzone.productservice.Products.Models.Product;
import com.eshoppingzone.productservice.Products.Models.ProductThumbnail;
import com.eshoppingzone.productservice.Products.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{


    ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        this.productRepository.save(product);
        log.info("Product  : "+product);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductThumbnail> getProductByTitle(String title, Pageable pageable) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(title);
        return this.productRepository.findAllBy(criteria,pageable);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProductById(String id) {
        this.productRepository.deleteById(id);
        log.warn("Product deleted with Id: "+id);
    }

    @Override
    public Page<Product> getProductByMerchantId(String merchantId, Pageable pageable) {
        return this.productRepository.findByMerchantId(merchantId,pageable);
    }

    @Override
    public Page<ProductThumbnail> getProductsByCategory(String category, String sub_category, Pageable pageable) {
//        Query query = new Query(
//                        Criteria
//                                .where("category")
//                                .elemMatch(Criteria.where("category_code").is(category).and("sub_category").is(sub_category))
//                ).with(pageable);
//
//        List<Product> list = mongoTemplate.find(query,Product.class);
//        return PageableExecutionUtils.getPage(list, pageable,
//                () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Product.class));

        return this.productRepository.findByCategory(category,sub_category,pageable);
    }


}
