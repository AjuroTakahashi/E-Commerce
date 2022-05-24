package com.ecommerce.controller;

import com.ecommerce.model.Client;
import com.ecommerce.model.ClientDto;
import com.ecommerce.service.ClientService;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("/ : Home page");
        model.addAttribute("products", productService.getAllProducts());
//        Client client = (Client)
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(client.getPassword());
        return "home";
    }
    @GetMapping("/login")
    public String login() {
        System.out.println("login page");
        return "login";
    }

//    @GetMapping("/logout")
//    public ModelAndView logout(Model model) {
//        System.out.println("logout");
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        authentication.
//        return new ModelAndView("redirect:/");
//    }

    @GetMapping("/bonjour")
    @PreAuthorize("'quack' == authentication.principal.username")
    public String quack() {
        System.out.println("quack page");
        return "home";
    }

//    @GetMapping("/client/registration")
//    public String clientRegistrationForm(WebRequest request, Model model) {
//
//        return "registration";
//    }
//
//    @PostMapping("/client/add")
//    public String register(@ModelAttribute("contact") ClientDto contactDto) {
//        ClientDto clientDto = new ClientDto();
//
//        try {
//            Client registered = clientService.registerNewClient(clientDto);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return "home";
//    }
}
