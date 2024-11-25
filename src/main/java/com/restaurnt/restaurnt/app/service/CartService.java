package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.model.Cart;
import com.restaurnt.restaurnt.app.model.CartItem;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req , String jwt)throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;


}
