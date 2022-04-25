package services;

import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;
import exceptions.StockException;

import java.util.ArrayList;
import java.util.Objects;

public class OrderServiceImpl implements OrderService{

    private ArrayList<Order> orderList = new ArrayList<>();

    @Override
    public ArrayList<Order> getAllOrders() {
        return orderList;
    }

    @Override
    public Order create(Order order) {
        order.setStatus("En cours.");
        if (!orderList.contains(order)) {
            orderList.add(order);
        }
        return order;
    }

    @Override
    public void update(Order order) throws StockException {
        if (!Objects.equals(order.getStatus(), "Payée.")) {
            for (Order o : orderList) {
                for (OrderProduct op : o.getOrderProducts()) {
                    Product product = op.getProduct();
                    if (order.checkStock(product, op.getQuantity())) {
                        product.setQuantity(product.getQuantity() - op.getQuantity());
                    } else {
                        throw new StockException();
                    }
                }
                order.setStatus("Payée.");
            }
        }
    }

    public void setProductService(ProductServiceImpl productService) {

    }
}
