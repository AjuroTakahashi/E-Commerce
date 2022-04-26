package com.ecommerce.services;

import com.ecommerce.model.Product;
import exceptions.StockException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service("products")
public class ProductServiceImpl implements ProductService {
    private ArrayList<Product> productsList = new ArrayList<>();

    private ProductServiceImpl() {
    }

    public ArrayList<Product> getAllProducts() {
        return productsList;
    }

    public Product getProductById(Long id) {
        Product result = null;
        for (Product product : productsList) {
            if (Objects.equals(product.getId(), id)) {
                result = product;
            }
        }
        return result;
    }

    public Product save(Product product) {
        if (!productsList.contains(product)) {
            productsList.add(product);
        }
        return product;
    }

    public boolean isProductAvailable(Product product, int quantity) {
        Product foundProduct = getProductById(product.getId());
        return foundProduct.getQuantity() >= quantity;
    }

    public void removeProduct(Product product, int quantity) throws StockException{

        System.out.println(product.getQuantity());
        if (isProductAvailable(product, quantity)) {
            Product foundProduct = getProductById(product.getId());
            foundProduct.setQuantity(foundProduct.getQuantity() - quantity);
        } else {
            throw new StockException();
        }
    }
}
