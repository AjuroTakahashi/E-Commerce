package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.exception.StockException;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("products")
public class ProductServiceImpl implements ProductService {
//    private ArrayList<Product> productsList = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;
    private ProductServiceImpl() {
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Product result = null;
        for (Product product : productRepository.findAll()) {
            if (Objects.equals(product.getId(), id)) {
                result = product;
            }
        }
        return result;
    }

    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }

    public boolean isProductAvailable(Product product, int quantity) {
        Product foundProduct = getProductById(product.getId());
        return foundProduct.getQuantity() >= quantity;
    }

    public void removeProduct(Product product, int quantity) throws StockException{

        if (isProductAvailable(product, quantity)) {
            Product foundProduct = getProductById(product.getId());
            foundProduct.setQuantity(foundProduct.getQuantity() - quantity);
        } else {
            throw new StockException();
        }
    }


}
