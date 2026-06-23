package org.example.service;

import org.example.entities.Compte;

import java.util.List;

public interface ICompteService {

    Long creerCompte(Compte compte);

    Compte consulterCompte(Long id);

    List<Compte> listerComptes();

    void modifierCompte(Compte compte);

    void supprimerCompte(Long id);
}