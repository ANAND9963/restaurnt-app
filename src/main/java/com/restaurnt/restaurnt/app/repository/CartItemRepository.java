package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.Cart;
import com.restaurnt.restaurnt.app.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem , Long> {

//    public  Cart findByCustomerId(Long userId);

}
