package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.exception.ResourceNotFoundException;

import java.util.ArrayList;

public interface ClientService {

    public ArrayList<Client> getClients();
    public Client getClientById(Long clientId) throws ResourceNotFoundException;
    public Client save(Client client);
}
