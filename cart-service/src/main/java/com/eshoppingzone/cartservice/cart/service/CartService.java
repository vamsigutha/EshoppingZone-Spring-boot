package com.eshoppingzone.cartservice.cart.service;

import com.eshoppingzone.cartservice.cart.models.Cart;




public interface CartService {
    Cart getCartByUserId(String userId);

    Cart addItemToCart(Cart cart);

    Cart updateCart(Cart cart);

    Cart deleteItemFromCart(Cart cart);
}
