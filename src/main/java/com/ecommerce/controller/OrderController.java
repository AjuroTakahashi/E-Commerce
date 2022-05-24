package com.ecommerce.controller;

import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.service.ClientService;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String getProductById(Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            Client client = clientService.getClientByName(username);
            model.addAttribute("order", orderService.getOrderByClient(client));
        } catch (Exception e) {
            System.out.println(e);
        }

        return "order";
    }
}
