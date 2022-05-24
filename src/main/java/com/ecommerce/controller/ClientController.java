package com.ecommerce.controller;

import com.ecommerce.model.Client;
import com.ecommerce.model.ClientDto;
import com.ecommerce.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/client/registration")
    public String clientRegistrationForm(WebRequest request, Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("client", clientDto);
        return "registration";
    }

    @PostMapping("/client/add")
    public ModelAndView register(@ModelAttribute("client") @Valid ClientDto clientDto, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                System.out.println(bindingResult.getAllErrors());
                return new ModelAndView("redirect:/client/registration");
            }
            Client registered = clientService.registerNewClient(clientDto);
        } catch (Exception e) {
            System.out.println(e);
            return new ModelAndView("redirect:/client/registration");
        }

        return new ModelAndView("redirect:/");
    }
}
