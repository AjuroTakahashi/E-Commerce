package services;

import com.ecommerce.model.Order;
import exceptions.StockException;

import java.util.ArrayList;

public interface OrderService {
    public ArrayList<Order> getAllOrders();
    public Order create(Order order);
    public void update(Order order) throws StockException;
}
