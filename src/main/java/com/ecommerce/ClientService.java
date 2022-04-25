package com.ecommerce;

import com.ecommerce.model.Client;

import java.util.ArrayList;

public class ClientService {
    private static ClientService INSTANCE;
    private ArrayList<Client> clients = new ArrayList<>();

    private ClientService() {
    }

    public static ClientService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClientService();
        }

        return INSTANCE;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void save(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
    }
}
