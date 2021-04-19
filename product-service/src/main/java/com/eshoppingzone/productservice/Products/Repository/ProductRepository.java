package com.eshoppingzone.productservice.Products.Repository;

import com.eshoppingzone.productservice.Products.Models.Product;
import com.eshoppingzone.productservice.Products.Models.ProductThumbnail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface ProductRepository extends MongoRepository<Product,String> {

    @Query(fields="{ 'id':1, 'title':1, 'price':1, 'images':1,'category':1}")
    Page<ProductThumbnail> findAllBy(TextCriteria criteria, Pageable pageable);



    @Query(value="{'category.category_code': ?0, 'category.sub_category': ?1}",fields="{ 'id':1, 'title':1, 'price':1, 'images':1,'category':1}")
    Page<ProductThumbnail> findByCategory(String category_code, String sub_category, Pageable pageable);

    Page<Product> findByMerchantId(String merchantId, Pageable pageable);

}
