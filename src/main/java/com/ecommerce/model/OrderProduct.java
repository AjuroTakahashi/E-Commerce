package com.ecommerce.model;

public class OrderProduct {

    private Integer quantity;
    private Product product;
    private Order order;

    public OrderProduct() {
        super();
    }
    public OrderProduct(Order order, Product product, Integer quantity)
    {
        super();
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "quantity=" + quantity +
                ", product=" + product +
                '}';
    }
}
