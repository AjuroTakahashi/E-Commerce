package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.exception.StockException;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product save(Product product);
    public boolean isProductAvailable(Product product, int quantity);
    public void removeProduct(Product product, int quantity) throws StockException;
}