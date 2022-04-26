package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service("clients")
public class ClientServiceImpl implements ClientService {
    private ArrayList<Client> clientsList = new ArrayList<>();

    private ClientServiceImpl() {
    }
    public ArrayList<Client> getClients() {
        return clientsList;
    }

    public Client getClientById(Long clientId) throws ResourceNotFoundException {
        Client foundClient = null;
        for (Client client : clientsList) {
            if (Objects.equals(client.getId(), clientId)) {
                foundClient = client;
            }
        }

        if (foundClient != null) {
            return foundClient;
        } else {
            throw new ResourceNotFoundException("-- Client Introuvable.");
        }
    }

    public Client save(Client client) {
        if (!clientsList.contains(client)) {
            clientsList.add(client);
        }
        return client;
    }
}