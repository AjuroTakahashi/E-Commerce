package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.exception.StockException;

import java.util.ArrayList;

public interface OrderService {
    public ArrayList<Order> getAllOrders();
    public Order create(Order order);
    public void update(Order order) throws StockException;
}
