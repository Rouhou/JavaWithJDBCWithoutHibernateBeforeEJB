package org.example.service;

import org.example.dao.CompteDaoImpl;
import org.example.dao.OperationDaoImpl;
import org.example.entities.Compte;
import org.example.entities.Operation;
import org.example.entities.TypeOperation;
import org.example.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;
import java.time.LocalDate;

public class OperationServiceImpl implements IOperationService {

    private CompteDaoImpl compteDao = new CompteDaoImpl();
    private OperationDaoImpl operationDao = new OperationDaoImpl();

    @Override
    public void depot(Long compteId, double montant) {

        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);

            Compte compte = compteDao.findById(compteId);

            compte.setSolde(compte.getSolde() + montant);

            compteDao.update(compte);

            Operation operation = new Operation(
                    null,LocalDate.now(), montant,
                    TypeOperation.DEPOT, compteId
            );

            operationDao.save(operation);

            con.commit();

            System.out.println("Dépôt effectué");

        } catch (Exception e) {

            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Erreur dépôt: rollback");
        }
    }

    @Override
    public void retrait(Long compteId, double montant) {

        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);

            Compte compte = compteDao.findById(compteId);

            if (compte.getSolde() < montant) {
                throw new RuntimeException("Solde insuffisant");
            }

            compte.setSolde(compte.getSolde() - montant);

            compteDao.update(compte);

            Operation operation = new Operation(
                    null,LocalDate.now(), montant,
                    TypeOperation.RETRAIT, compteId
            );

            operationDao.save(operation);

            con.commit();

            System.out.println("Retrait effectué");

        } catch (Exception e) {

            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Erreur retrait: rollback");
        }
    }

    @Override
    public void virement(Long sourceId, Long destId, double montant) {

        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);

            Compte source = compteDao.findById(sourceId);
            Compte dest = compteDao.findById(destId);

            if (source.getSolde() < montant) {
                throw new RuntimeException("Solde insuffisant");
            }

            source.setSolde(source.getSolde() - montant);
            dest.setSolde(dest.getSolde() + montant);

            compteDao.update(source);
            compteDao.update(dest);

            Operation retrait = new Operation(
                    null,LocalDate.now(), montant,
                    TypeOperation.RETRAIT, sourceId
            );

            Operation depot = new Operation(
                    null, LocalDate.now(), montant,
                    TypeOperation.DEPOT, destId
            );

            operationDao.save(retrait);
            operationDao.save(depot);

            con.commit();

            System.out.println("Virement effectué");

        } catch (Exception e) {

            try {
                if (con != null) con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Erreur virement: rollback");
        }
    }

    public List<Operation> listerOperation(){
        return operationDao.findAll();
    }

    public void supprimerOperation(Long id){
        try (Connection con = DatabaseConnection.getConnection()) {

            con.setAutoCommit(false);

            operationDao.delete(id);
            System.out.println("Operation "+id+ " supprimee");

            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}