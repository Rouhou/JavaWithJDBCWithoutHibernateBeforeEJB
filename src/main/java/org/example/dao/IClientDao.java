package org.example.dao;

import org.example.entities.Client;
import java.util.List;

public interface IClientDao {

    Long save(Client client);

    Client findById(Long id);

    List<Client> findAll();

    void update(Client client);

    void delete(Long id);
}
