package org.example.service;

import org.example.entities.Client;

import java.util.List;

public interface IClientService {

    Long creerClient(Client client);

    Client consulterClient(Long id);

    List<Client> listerClients();

    void modifierClient(Client client);

    void supprimerClient(Long id);
}