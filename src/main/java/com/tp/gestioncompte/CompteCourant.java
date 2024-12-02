package com.tp.gestioncompte;

public class CompteCourant extends Compte {
    private double decouvertAutorise;

    public CompteCourant(String proprietaire, double decouvertAutorise) {
        super(proprietaire);
        this.decouvertAutorise = decouvertAutorise;
    }

    // Méthode spécifique pour afficher le compte courant
    @Override
    public void information() {
        System.out.println("Résumé du compte courant de " + proprietaire);
        System.out.println("*******************************************");
        super.information();  // Affiche le solde de base
        System.out.println("Découvert autorisé : " + String.format("%.2f", decouvertAutorise));
        System.out.println("Opérations :");
        for (Operation op : operations) {
            String type = op.getType() == Mouvement.CREDIT ? "+" : "-";
            System.out.println(type + String.format("%.2f", op.getMontant()));
        }
        System.out.println("*******************************************");
    }

    @Override
    public double calculBenefice() {
        return 0;  // Pas de bénéfice pour un compte courant
    }
}
