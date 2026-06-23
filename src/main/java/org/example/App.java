package org.example;

import org.example.entities.Client;
import org.example.entities.Compte;
import org.example.entities.TypeOperation;
import org.example.entities.Operation;
import org.example.service.ClientServiceImpl;
import org.example.service.CompteServiceImpl;
import org.example.service.OperationServiceImpl;

import java.time.LocalDate;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ClientServiceImpl clientService = new ClientServiceImpl();
        CompteServiceImpl compteService = new CompteServiceImpl();
        OperationServiceImpl operationService = new OperationServiceImpl();

        List<Operation> listeOperation =  operationService.listerOperation();
        for (Operation operation : listeOperation) {
            operationService.supprimerOperation(operation.getId());
        }

        List<Compte> listeCompte = compteService.listerComptes();
        for(Compte compte : listeCompte) {
            compteService.supprimerCompte(compte.getId());
        }

        List<Client> listeClient = clientService.listerClients();
        for(Client client: listeClient){
            clientService.supprimerClient(client.getId());
        }


        //Creation client
        Client c1 = new Client(null, "Issa", "Diagne", "770000001", LocalDate.of(1990, 1, 10));
        Client c2 = new Client(null, "Ignace Eine", "Diatta", "770000002", LocalDate.of(1992, 2, 15));
        Client c3 = new Client(null, "Jean Yves", "Yowane", "770000003", LocalDate.of(1988, 5, 20));
        Client c4 = new Client(null, "Samba", "Suare", "770000004", LocalDate.of(1995, 7, 12));
        Client c5 = new Client(null, "Ibrahima", "Ndiaye", "770000005", LocalDate.of(1991, 11, 30));

        Long clientIdIssa = clientService.creerClient(c1);
        Long clientIdIgnace = clientService.creerClient(c2);
        Long clientIdJean = clientService.creerClient(c3);
        Long clientIdSamba = clientService.creerClient(c4);
        Long clientIdIbrahima = clientService.creerClient(c5);

        // Creation compte
        Compte cp1 = new Compte(null, "CPT001", 100000.0, LocalDate.now(), clientIdIssa);
        Compte cp2 = new Compte(null, "CPT002", 200000.0, LocalDate.now(), clientIdIgnace);
        Compte cp3 = new Compte(null, "CPT003", 300000.0, LocalDate.now(), clientIdJean);
        Compte cp4 = new Compte(null, "CPT004", 400000.0, LocalDate.now(), clientIdSamba);
        Compte cp5 = new Compte(null, "CPT005", 500000.0, LocalDate.now(), clientIdIbrahima);

        Long compteIdIssa = compteService.creerCompte(cp1);
        Long compteIdIgnace = compteService.creerCompte(cp2);
        Long compteIdJean = compteService.creerCompte(cp3);
        Long compteIdSamba = compteService.creerCompte(cp4);
        Long compteIdIbrahima = compteService.creerCompte(cp5);

        // Dépôts
        operationService.depot(compteIdIssa, 50000);
        operationService.depot(compteIdIgnace, 25000);
        operationService.depot(compteIdJean, 10000);
        operationService.depot(compteIdSamba, 75000);
        operationService.depot(compteIdIbrahima, 120000);

        // Retraits
        operationService.retrait(compteIdIssa, 20000);
        operationService.retrait(compteIdIgnace, 50000);
        operationService.retrait(compteIdJean, 15000);
        operationService.retrait(compteIdSamba, 100000);
        operationService.retrait(compteIdIbrahima, 30000);

        // Virements
        operationService.virement(compteIdIssa, compteIdIgnace, 10000);
        operationService.virement(compteIdIbrahima, compteIdJean, 50000);
        operationService.virement(compteIdSamba, compteIdIssa, 25000);
        operationService.virement(compteIdIgnace, compteIdIbrahima, 30000);



    }
}
