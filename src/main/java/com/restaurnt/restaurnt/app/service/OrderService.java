package com.restaurnt.restaurnt.app.service;

import com.restaurnt.restaurnt.app.model.Order;
import com.restaurnt.restaurnt.app.model.User;
import com.restaurnt.restaurnt.app.request.OrderRequest;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order , User user) throws Exception;

    public Order updateOrder(Long orderId , String orderStatus) throws Exception;

    public  void  cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userId) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId , String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

}
