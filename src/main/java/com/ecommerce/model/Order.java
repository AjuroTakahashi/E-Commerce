package com.ecommerce.model;

import java.time.LocalDate;
import java.util.*;

public class Order {

    private Long id;
    private LocalDate dateCreated;
    private String status;
    private HashSet<OrderProduct> orderProducts = new HashSet<>();
    private Client client;

    public Order() {
        super();
    }

    public Order(Long id, LocalDate dateCreated, String status, HashSet<OrderProduct> orderProducts, Client client) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.status = status;
        if (orderProducts != null) {
            this.orderProducts = orderProducts;
        }
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public HashSet<OrderProduct> getOrderProduct() {
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

    public Double getTotaOrderlPrice() {
        Double result = 0.00;

        for (OrderProduct orderProduct : orderProducts) {
            result += orderProduct.getTotalPrice();
        }

        return result;
    }

    public Integer getNumberOfProducts() {
        ArrayList<Long> productList = new ArrayList<>();

        for (OrderProduct orderProduct : orderProducts) {
            Long productId = orderProduct.getProduct().getId();

            if (!productList.contains(productId)) {
                productList.add(productId);
            }
        }

        return productList.size();
    }

    public Integer getTotalNumberOfProducts() {
        int result = 0;

        for (OrderProduct orderProduct : orderProducts) {
            result += 1;
        }

        return result;
    }

    public void addProduct(Product product, int quantity) {

        boolean productExists = false;

        for (OrderProduct orderProduct : orderProducts) {
            if (Objects.equals(orderProduct.getProduct().getId(), product.getId()) && checkStock(product, quantity)) {
                    orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
                    product.setQuantity(product.getQuantity() - quantity);
                    productExists = true;
            }
        }

        if (!productExists) {
            if (checkStock(product, quantity)) {
                OrderProduct newOrderProduct = new OrderProduct();
                newOrderProduct.setProduct(product);
                newOrderProduct.setQuantity(quantity);
                product.setQuantity(product.getQuantity() - quantity);
                orderProducts.add(newOrderProduct);
            } else {
                System.out.println("Pas assez de " + product.getName());
            }
        }
    }

    public boolean checkStock(Product product, int quantity) {
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
