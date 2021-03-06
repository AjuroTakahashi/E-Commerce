package com.ecommerce.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name="Client_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreated;
    private String status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts;
    @ManyToOne
    private Client client;

    public Order() {
        super();
    }

    public Order(Long id, LocalDate dateCreated, String status, Set<OrderProduct> orderProducts, Client client) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.status = status;

        System.out.println("construit");

//        if (orderProducts != null) {
//            System.out.println(orderProducts.size());
//            this.orderProducts = orderProducts;
//        }
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public Client getClient() {
        return client;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double gettotalOrderPrice() {
        Double result = 0.00;

        for (OrderProduct orderProduct : orderProducts) {
            result += orderProduct.getTotalPrice();
        }

        return result;
    }

    public Integer numberOfProducts() {
        return orderProducts.size();
    }

    public Integer getTotalNumberOfProducts() {
        int result = 0;

        for (OrderProduct orderProduct : orderProducts) {
            result += orderProduct.getQuantity();
        }

        return result;
    }

    public void addProduct(Product product, int quantity) throws Exception {

        OrderProduct orderProduct = null;

//        if (orderProducts != null) {
        for (OrderProduct op : orderProducts) {
            if (Objects.equals(op.getProduct().getId(), product.getId())) {
//                orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
//                product.setQuantity(product.getQuantity() - quantity);
                orderProduct = op;
                op.setQuantity(op.getQuantity() + quantity);
            }
        }
//        }

        if (orderProduct == null) {
//            if (isAvailable(product, quantity)) {
            OrderProductId orderProductId = new OrderProductId(product.getId(), id);
            orderProduct = new OrderProduct(orderProductId, product, this, quantity);
            orderProducts.add(orderProduct);
//                product.setQuantity(product.getQuantity() - quantity);
//                orderProducts.add(newOrderProduct);
//                result = new OrderProduct();
//                result.setOrder(this);
//                result.setProduct(product);
//                result.setQuantity(quantity);
//            } else {
//                throw new Exception("-- Pas assez de " + product.getName());
//            }
        }
    }

    public boolean isAvailable(Product product, int quantity) {
        return product.getQuantity() >= quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", status='" + status + '\'' +
                ", orderProducts=" + orderProducts +
                ", client=" + client +
                '}';
    }
}
