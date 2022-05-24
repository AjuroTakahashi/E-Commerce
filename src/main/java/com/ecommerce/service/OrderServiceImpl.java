package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;
import com.ecommerce.exception.StockException;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("orders")
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order create(Order order) {
        order.setStatus("En cours.");
        orderRepository.save(order);
        return order;
    }
    @Override
    public void update(Order order) throws StockException {

        System.out.println(order.getOrderProducts());
        if (!Objects.equals(order.getStatus(), "Payée.")) {
            for (OrderProduct op : order.getOrderProducts()) {
                Product product = op.getProduct();
                if (!productService.isProductAvailable(product, op.getQuantity())) {
                    throw new StockException();
                }
            }
            order.setStatus("Payée.");

            for (OrderProduct op : order.getOrderProducts()) {
                Product product = op.getProduct();
                if (productService.isProductAvailable(product, op.getQuantity())) {
                    productService.removeProduct(product, op.getQuantity());
                }
            }
        }
    }

    @Override
    public Order getOrderByClient(Client client) {

        Order order = null;
        for (Order o: getAllOrders()) {
            if (o.getClient() == client) {
                order = o;
            }
        }
        return order;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getCurrentOrder(Client client){

        Optional<Order> orderOptional =  orderRepository.findByClientAndStatus(client, "En cours");

        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }
        else{
            Date date = new Date();
            Order order = new Order(null, new java.sql.Timestamp(
                    date.getTime()).toLocalDateTime().toLocalDate(), "En cours", new HashSet<>(), client);

            return orderRepository.save(order);
        }
    }

    @Override
    public void addOrderProduct(Order order, Product product, int quantity) throws Exception {
//        return getOrder(order.getId()).addProduct(product, quantity);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
