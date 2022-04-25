package services;

import com.ecommerce.model.Product;
import exceptions.StockException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public interface ProductService {
    public ArrayList<Product> getAllProducts();
    public Product getProductById(Long id);
    public Product save(Product product);
    public boolean isProductAvailable(Product product, int quantity);
    public void removeProduct(Product product, int quantity) throws StockException;
}