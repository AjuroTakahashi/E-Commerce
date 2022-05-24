package com.ecommerce.controller;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ClientService;
import com.ecommerce.service.OrderProductService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private ProductRepository productRepository;

//    @GetMapping(value = { "/", "/" })
//    public String getProducts(Model model) {
//        System.out.println("/products : get all products");
//        model.addAttribute("products", productService.getAllProducts());
//        return "products";
//    }
    @GetMapping("/{productId}")
    public String getProductById(Model model, @PathVariable("productId") Long id) {
        model.addAttribute("product", productRepository.getById(id));
        return "productSingle";
    }

    @GetMapping("/add/{productId}")
    public ModelAndView addProductToCart(Model model, @PathVariable("productId") Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Product product = productService.getProductById(id);

        try {
            Client client = clientService.getClientByName(username);
            Order order = orderService.getCurrentOrder(client);
            order.addProduct(product, 1);
            orderService.save(order);


//            Order order = new Order();
//            order.setClient(client);
//            Date date = new Date();
//            order.setDateCreated(new java.sql.Timestamp(
//                    date.getTime()).toLocalDateTime().toLocalDate());
//            order.setStatus("En cours");
//            orderService.save(order);
//            System.out.println("product order : " + orderService.addOrderProduct(order, product, 1));
//            orderProductService.save(orderService.addOrderProduct(order, product, 1));
//            orderService.save(order);
        } catch (Exception e) {
            System.out.println(e);
            return new ModelAndView("redirect:/products/" + id );
        }

        return new ModelAndView("redirect:/products/" + id );
    }
}
