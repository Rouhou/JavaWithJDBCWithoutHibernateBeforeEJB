package org.example.service;

import org.example.dao.ClientDaoImpl;
import org.example.entities.Client;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class ClientServiceImpl implements IClientService {

    private final ClientDaoImpl clientDao = new ClientDaoImpl();

    @Override
    public Long creerClient(Client client) {

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            Long clientId = clientDao.save(client);

            con.commit();

            System.out.println("Client créé");
            return clientId;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client consulterClient(Long id) {
        return clientDao.findById(id);
    }

    @Override
    public List<Client> listerClients() {
        return clientDao.findAll();
    }

    @Override
    public void modifierClient(Client client) {

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            clientDao.update(client);

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerClient(Long id) {

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            clientDao.delete(id);
            System.out.println("Client "+id+ " supprime");

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}