package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface ClientService {

    public List<Client> getClients();
    public Client getClientById(Long clientId) throws ResourceNotFoundException;
    public Client getClientByName(String name) throws ResourceNotFoundException;

    public Client save(Client client);
}
