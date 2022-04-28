package com.ecommerce.controller;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Client;
import com.ecommerce.model.Order;
import com.ecommerce.service.ClientService;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/clients")
    public List<Client> getClients(Model model, @RequestParam(required = false) String username) throws ResourceNotFoundException {
        System.out.println("/clients : get all clients " + username);

        if (username != null) {
            return List.of(clientService.getClientByName(username));
        }
        return clientService.getClients();
    }

    @GetMapping(value = "/orders")
    public List<Order> getOrder(Model model, @RequestParam(required = false) Long id) throws ResourceNotFoundException {
        System.out.println("/orders : " + id);

        if (id != null) {
            return List.of(orderService.getOrder(id));
        }
        return orderService.getAllOrders();
    }
}
