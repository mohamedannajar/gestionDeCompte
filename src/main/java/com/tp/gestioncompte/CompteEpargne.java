package com.tp.gestioncompte;

public class CompteEpargne extends Compte {
    private double tauxAbondement;  // Taux d'abondement pour le compte épargne

    public CompteEpargne(String proprietaire, double tauxAbondement) {
        super(proprietaire);
        this.tauxAbondement = tauxAbondement;
    }

    // Calcul du bénéfice avec taux d'abondement
    @Override
    public double calculBenefice() {
        // Le bénéfice est calculé en fonction du solde actuel * taux d'abondement
        return calculSolde() * tauxAbondement / 100;
    }

    @Override
    public void information() {
        // Affichage du résumé du compte épargne
        System.out.println("Résumé du compte épargne de " + proprietaire);
        System.out.println("*******************************************");
        super.information();  // Affiche le solde de base et les opérations
        double soldeAvecAbondement = calculSolde() + calculBenefice();
        System.out.println("Taux d’abondement : " + tauxAbondement + " %");
        System.out.println("Opérations :");
        for (Operation op : operations) {
            String type = op.getType() == Mouvement.CREDIT ? "+" : "-";
            System.out.println(type + String.format("%.2f", op.getMontant()));
        }
        System.out.println("Solde final : " + String.format("%.2f", soldeAvecAbondement));
        System.out.println("*******************************************");
    }
}
