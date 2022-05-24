package com.ecommerce.service;

import com.ecommerce.model.Client;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.ClientDto;
import com.ecommerce.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("clients")
public class ClientServiceImpl implements ClientService, UserDetailsService {
//    private ArrayList<Client> clientsList = new ArrayList<>();
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ClientServiceImpl() {
    }
    public List<Client> getClients() {
//        List<Client> clients = null;
//        for (Client client : clientRepository.findAll()) {
//            clients.add(client);
//        }
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

//    public Client save(Client client) {
//        clientRepository.save(client);
//        return client;
//    }

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findByUsername(username);
        return client.get();
    }

    public Client save(Client client) throws Exception {
        for (Client c : getClients()) {
            if (Objects.equals(c.getUsername(), client.getUsername())){
                System.out.println("There is an account with that username:" + client.getUsername());
//                throw new Exception(
//                        "There is an account with that username:" + client.getUsername());
            }
        }
        Client user = new Client();
        client.setUsername(client.getUsername());
        client.setPassword(passwordEncoder.encode(client.getPassword()));

//        user.setRole(new Role(Integer.valueOf(1), user));
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client registerNewClient(ClientDto clientDto) throws Exception {
        for (Client c : getClients()) {
            if (Objects.equals(c.getUsername(), clientDto.getUsername())){
                throw new Exception(
                        "There is an account with that username:" + clientDto.getUsername());
            }
        }

        System.out.println("Enregistré");
        Client client = new Client();
        client.setUsername(clientDto.getUsername());
        client.setPassword(passwordEncoder.encode(clientDto.getPassword()));
        System.out.println(client);

        return clientRepository.save(client);
    }

    public void updateClient(Client client) {
        for (Client c : getClients()) {
            if (Objects.equals(c.getUsername(), client.getUsername())){
                c.setPassword(client.getPassword());
                clientRepository.save(c);
            }
        }
    }
}
