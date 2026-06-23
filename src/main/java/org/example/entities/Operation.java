package org.example.entities;

import java.time.LocalDate;

public class Operation {

    private Long id;
    private LocalDate dateOperation;
    private Double montant;
    private TypeOperation type;
    private Long compteId;

    public Operation() {
    }

    public Operation(Long id, LocalDate dateOperation,
                     Double montant, TypeOperation type,
                     Long compteId) {
        this.id = id;
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.type = type;
        this.compteId = compteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public TypeOperation getType() {
        return type;
    }

    public void setType(TypeOperation type) {
        this.type = type;
    }

    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long compteId) {
        this.compteId = compteId;
    }

    public boolean estDepot() {
        return type == TypeOperation.DEPOT;
    }

    public boolean estRetrait() {
        return type == TypeOperation.RETRAIT;
    }

    public boolean montantValide() {
        return montant != null && montant > 0;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", dateOperation=" + dateOperation +
                ", montant=" + montant +
                ", type=" + type +
                ", compteId=" + compteId +
                '}';
    }
}