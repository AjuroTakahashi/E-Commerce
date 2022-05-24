package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.exception.StockException;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrder(Long id);
    Order create(Order order);
    void update(Order order) throws StockException;
    Order getOrderByClient(Client client);
    void save(Order order);

    Order getCurrentOrder(Client client);

    void addOrderProduct(Order order, Product product, int quantity) throws Exception;
}
