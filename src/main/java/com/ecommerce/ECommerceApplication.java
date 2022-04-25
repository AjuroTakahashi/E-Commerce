package com.ecommerce;

import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

//@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ECommerceApplication.class, args);
        Product p1 = new Product(1L, "Jambon", "Viande", "photo", 2.0, 10);
        Product p2 = new Product(2L, "Baguette", "Pain", "photo", 3.0, 22);
        Product p3 = new Product(3L, "Tomate", "Légume", "photo", 4.0, 13);
        Product p4 = new Product(4L, "Fromage", "Froid", "photo", 5.0, 8);

        Client client = new Client(1L, "Jean", "1234");
        Order order = new Order(1L, LocalDate.now(), "en cours", null, client);

        order.addProduct(p1, 2);
        order.addProduct(p1, 8);
        order.addProduct(p2, 4);
        order.addProduct(p3, 4);

        order.setStatus("Payé");
        System.out.println(order.getNumberOfProducts());
        System.out.println(order.getTotalNumberOfProducts());
        System.out.println(order.getTotaOrderlPrice());

        System.out.println(order);
    }

}
