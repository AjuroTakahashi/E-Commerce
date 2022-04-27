package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

//    @GetMapping(value = { "/", "/" })
//    public String getProducts(Model model) {
//        System.out.println("/products : get all products");
//        model.addAttribute("products", productService.getAllProducts());
//        return "products";
//    }

    @GetMapping("/{productId}")
    public String getProductById(Model model, @PathVariable("productId") Long id) {
        System.out.println("/products/id : get product by id (" + id + ")");
        model.addAttribute("product", productService.getProductById(id));
        return "base";
    }
}
