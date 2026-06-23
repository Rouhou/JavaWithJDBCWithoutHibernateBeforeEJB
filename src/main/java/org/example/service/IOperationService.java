package org.example.service;

import org.example.entities.Operation;

import java.util.List;

public interface IOperationService {

    void depot(Long compteId, double montant);

    void retrait(Long compteId, double montant);

    void virement(Long sourceId, Long destId, double montant);

    List<Operation> listerOperation();

    void supprimerOperation(Long id);
}