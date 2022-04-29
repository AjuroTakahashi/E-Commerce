package com.ecommerce.controller;

import com.ecommerce.model.Client;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("/ : Home page");
        model.addAttribute("products", productService.getAllProducts());
//        Client client = (Client)
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(client.getPassword());
        return "home";
    }

    @GetMapping("/bonjour")
    public String printBonjour() {
        return "Ici on dit bonjour";
    }
}
