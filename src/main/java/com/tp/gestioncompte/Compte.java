package com.tp.gestioncompte;

import java.util.ArrayList;
import java.util.List;

public abstract class Compte implements Comparable<Compte> {
    protected String proprietaire;
    protected List<Operation> operations;

    public Compte(String proprietaire) {
        this.proprietaire = proprietaire;
        this.operations = new ArrayList<>();
    }

    // Méthode pour créditer le compte
    public void crediter(double montant) {
        operations.add(new Operation(montant, Mouvement.CREDIT));
    }

    // Méthode pour débiter le compte
    public void debiter(double montant) {
        operations.add(new Operation(montant, Mouvement.DEBIT));
    }

    // Nouvelle méthode pour effectuer un virement
    public void virement(double montant, Compte destination) {
        if (montant <= 0) {
            System.out.println("Le montant du virement doit être positif.");
            return;
        }

        if (this.calculSolde() >= montant) {
            this.debiter(montant);
            destination.crediter(montant);
            System.out.println("Virement de " + String.format("%.2f", montant) + " effectué avec succès de " +
                    this.proprietaire + " vers " + destination.proprietaire + ".");
        } else {
            System.out.println("Fonds insuffisants pour effectuer le virement.");
        }
    }

    // Calcul du solde
    public double calculSolde() {
        double solde = 0;
        for (Operation op : operations) {
            solde += (op.getType() == Mouvement.CREDIT ? op.getMontant() : -op.getMontant());
        }
        return solde;
    }

    public abstract double calculBenefice();

    // Calcul du solde final
    public double soldeFinal() {
        return calculSolde() + calculBenefice();
    }

    // Affichage des informations générales
    public void information() {
        System.out.println("Propriétaire : " + proprietaire);
        System.out.println("Solde : " + String.format("%.2f", calculSolde()));
    }

    @Override
    public int compareTo(Compte other) {
        return Double.compare(this.calculSolde(), other.calculSolde());
    }
}
