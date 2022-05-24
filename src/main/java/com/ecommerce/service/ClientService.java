package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.ClientDto;

import java.util.ArrayList;
import java.util.List;

public interface ClientService {

    List<Client> getClients();
    Client getClientById(Long clientId) throws ResourceNotFoundException;
    Client getClientByName(String name) throws ResourceNotFoundException;
    Client save(Client client) throws Exception;
    void updateClient(Client client);
    Client registerNewClient(ClientDto clientDto) throws Exception;
}
