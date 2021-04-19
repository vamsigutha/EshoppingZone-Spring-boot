package com.eshoppingzone.cartservice.cart.service;

import com.eshoppingzone.cartservice.cart.models.Cart;
import com.eshoppingzone.cartservice.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCartByUserId(String userId) {
        return this.cartRepository.findByUserId(userId);
    }


    @Override
    public Cart addItemToCart(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart deleteItemFromCart(Cart cart) {
        return this.cartRepository.save(cart);
    }


}
