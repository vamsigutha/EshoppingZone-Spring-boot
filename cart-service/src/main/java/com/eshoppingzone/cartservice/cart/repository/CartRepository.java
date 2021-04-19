package com.eshoppingzone.cartservice.cart.repository;


import com.eshoppingzone.cartservice.cart.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CartRepository extends MongoRepository<Cart,String> {

    Cart findByUserId(String userId);

//    @Query("{'userId':?0, 'items.productId':?1}")
//    Cart findByUserIdAndProductId(String userId, String productId);

}
