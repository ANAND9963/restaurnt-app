package com.restaurnt.restaurnt.app.repository;

import com.restaurnt.restaurnt.app.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
