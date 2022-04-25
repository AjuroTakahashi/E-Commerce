package com.ecommerce.model;

public class Product {

    private Long id;
    private String name;
    private String description;
    private String picture;
    private Double price;
    private Integer quantity;

    public Product() {
        super();
    }

    public Product(Long id, String name, String description, String picture, Double price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
