package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart , Long> {

}
