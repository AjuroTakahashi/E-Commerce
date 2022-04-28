package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.exception.StockException;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public Order getOrder(Long id);
    public Order create(Order order);
    public void update(Order order) throws StockException;
}
