package org.example.service;

import org.example.dao.CompteDaoImpl;
import org.example.entities.Compte;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class CompteServiceImpl implements ICompteService {

    private final CompteDaoImpl compteDao = new CompteDaoImpl();

    @Override
    public Long creerCompte(Compte compte) {

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            Long compteId = compteDao.save(compte);

            con.commit();

            System.out.println("Compte créé");
            return compteId;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Compte consulterCompte(Long id) {
        return compteDao.findById(id);
    }

    @Override
    public List<Compte> listerComptes() {
        return compteDao.findAll();
    }

    @Override
    public void modifierCompte(Compte compte) {

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            compteDao.update(compte);

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerCompte(Long id) {

        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            compteDao.delete(id);
            System.out.println("Compte "+id+ " supprime");

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}