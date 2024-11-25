package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order , Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);


}
