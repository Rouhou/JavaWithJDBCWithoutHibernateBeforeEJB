package org.example.dao;

import org.example.entities.Compte;

import java.util.List;

public interface ICompteDao {

    Long save(Compte compte);

    Compte findById(Long id);

    List<Compte> findAll();

    void update(Compte compte);

    void delete(Long id);
}