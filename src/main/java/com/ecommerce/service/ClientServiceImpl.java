package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("clients")
public class ClientServiceImpl implements ClientService {
//    private ArrayList<Client> clientsList = new ArrayList<>();
    @Autowired
    private ClientRepository clientRepository;

    private ClientServiceImpl() {
    }
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long clientId) throws ResourceNotFoundException {
        Client foundClient = null;
        for (Client client : clientRepository.findAll()) {
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
        clientRepository.save(client);
        return client;
    }

    public Client getClientByName(String name) throws ResourceNotFoundException {
        Client foundClient = null;
        for (Client client : clientRepository.findAll()) {
            if (Objects.equals(client.getUsername(), name)) {
                foundClient = client;
            }
        }

        if (foundClient != null) {
            return foundClient;
        } else {
            throw new ResourceNotFoundException("-- Client Introuvable.");
        }
    }
}
