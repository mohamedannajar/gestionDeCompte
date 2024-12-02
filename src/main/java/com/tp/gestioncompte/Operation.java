package com.tp.gestioncompte;

public class Operation {
    private double montant;
    private Mouvement type;

    public Operation(double montant, Mouvement type) {
        this.montant = montant;
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public Mouvement getType() {
        return type;
    }
}
