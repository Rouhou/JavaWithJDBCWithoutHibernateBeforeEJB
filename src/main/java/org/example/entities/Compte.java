package org.example.entities;

import java.time.LocalDate;

public class Compte {

    private Long id;
    private String numeroCompte;
    private Double solde;
    private LocalDate dateCreation;
    private Long clientId;

    public Compte() {
    }

    public Compte(Long id, String numeroCompte, Double solde,
                  LocalDate dateCreation, Long clientId) {
        this.id = id;
        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id=" + id +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", solde=" + solde +
                ", dateCreation=" + dateCreation +
                ", clientId=" + clientId +
                '}';
    }
}