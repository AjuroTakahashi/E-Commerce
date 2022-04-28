package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;
import com.ecommerce.exception.StockException;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("orders")
public class OrderServiceImpl implements OrderService{

//    private ArrayList<Order> orderList = new ArrayList<>();

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

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
