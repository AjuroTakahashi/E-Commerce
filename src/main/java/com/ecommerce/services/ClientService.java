package com.ecommerce.services;

import com.ecommerce.model.Client;
import exceptions.ResourceNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

public interface ClientService {

    public ArrayList<Client> getClients();
    public Client getClientById(Long clientId) throws ResourceNotFoundException;
    public Client save(Client client);
}
