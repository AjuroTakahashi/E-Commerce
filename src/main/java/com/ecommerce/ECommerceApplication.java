package com.ecommerce;

import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.ecommerce.service.ClientService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ECommerceApplication {

    public static void mainTP12(String[] args) throws Exception {
//        SpringApplication.run(ECommerceApplication.class, args);
        Product p1 = new Product(1L, "Jambon", "Viande", "photo", 2.0, 10);
        Product p2 = new Product(2L, "Baguette", "Pain", "photo", 3.0, 22);
        Product p3 = new Product(3L, "Tomate", "Légume", "photo", 4.0, 13);
        Product p4 = new Product(4L, "Fromage", "Froid", "photo", 5.0, 8);

        Client client = new Client(1L, "Jean", "1234");
        Client client2 = new Client(2L, "Dupont", "1234");

        Order order = new Order(1l, LocalDate.now(), null, new ArrayList<OrderProduct>(), client);

        ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/resources/services.xml");
        ClientService clientService = context.getBean("clients", ClientService.class);
        clientService.save(client);
        clientService.save(client);
        clientService.save(client2);
//        System.out.println(clientService.getClientById(2L));

        ProductService productService = context.getBean("products", ProductService.class);
        productService.save(p1);
        productService.save(p2);
        productService.save(p3);
//        productService.removeProduct(p1, 10);

        OrderService orderService = context.getBean("orders", OrderService.class);

        order.addProduct(p1, 2); // Vérifié avant, dans la création de l'order
        order.addProduct(p2, 5);

        System.out.println(order); // Le statut doit être à “null”

        orderService.create(order);
        System.out.println(order); // Le statut doit être à “En cours”

        orderService.update(order);
        System.out.println(order); // Le statut doit être à “Payée” (sauf si vérifié avant)

//        System.out.println(clientService.getClients());
//
//        order.setStatus("Payé");
//        System.out.println(order.getNumberOfProducts());
//        System.out.println(order.getTotalNumberOfProducts());
//        System.out.println(order.getTotaOrderlPrice());
//
//        System.out.println(order);
    }

    // @ComponentScan("com.*") // Rajouter sur la classe pour que ça marche
    public static void mainTP3(String[] args) throws Exception {
        Product p1 = new Product(1L, "Jambon", "Viande", "photo", 2.0, 10);
        Product p2 = new Product(2L, "Baguette", "Pain", "photo", 3.0, 22);
        Product p3 = new Product(3L, "Tomate", "Légume", "photo", 4.0, 13);
        Product p4 = new Product(4L, "Fromage", "Froid", "photo", 5.0, 8);

        Client client = new Client(1L, "Jean", "1234");
        Client client2 = new Client(2L, "Dupont", "1234");

        Order order = new Order(1l, LocalDate.now(), null, new ArrayList<OrderProduct>(), client);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ECommerceApplication.class);
        ClientService clientService = context.getBean("clients", ClientService.class);
//        System.out.println(clientService.getClientById(2L));

        clientService.save(client);
        clientService.save(client);
        clientService.save(client2);
//        System.out.println(clientService.getClientById(2L));

        ProductService productService = context.getBean("products", ProductService.class);
        productService.save(p1);
        productService.save(p2);
        productService.save(p3);
//        productService.removeProduct(p1, 10);

        OrderService orderService = context.getBean("orders", OrderService.class);

        order.addProduct(p1, 2); // Vérifié avant, dans la création de l'order
        order.addProduct(p2, 5);

        System.out.println(order); // Le statut doit être à “null”

        orderService.create(order);
        System.out.println(order); // Le statut doit être à “En cours”

        orderService.update(order);
        System.out.println(order); // Le statut doit être à “Payée” (sauf si vérifié avant)
    }

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ProductService productService) {
        return args -> {
            Product product1 = new Product(1l, "Rien", "Basique, rien","../../resources/images/mokoko2.webp", 12d,2);
            Product product2 = new Product(2l, "Rien premium", "C'est peut-être rien mais c'est premium, ça vaut bien quelque chose", "../../resources/images/mokoko3.webp", 32d,4);
            Product product3 = new Product(3l, "Rien 1/2", "Un peu plus que rien, mais c'est toujours rien", "../../resources/images/mokoko4.webp", 20d,28);
            Product product4 = new Product(4l, "Rien 1/2", "Un peu plus que rien, mais c'est toujours rien", "../../resources/images/mokoko4.webp", 20d,28);
            productService.save(product1);
            productService.save(product2);
            productService.save(product3);
            productService.save(product4);
        };
    }
}
