package com.tp.gestioncompte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Compte> comptes = new ArrayList<>();

    public static void main(String[] args) {
        // Création initiale des comptes et ajout à la liste
        initialiserComptes();

        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            afficherMenu();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1 -> creerCompteCourant(scanner);
                case 2 -> creerCompteEpargne(scanner);
                case 3 -> crediterCompte(scanner);
                case 4 -> debiterCompte(scanner);
                case 5 -> effectuerVirement(scanner);
                case 6 -> afficherComptes();
                case 7 -> trierComptes();
                case 8 -> System.out.println("Au revoir !");
                default -> System.out.println("Choix invalide, veuillez réessayer.");
            }
        } while (choix != 8);

        scanner.close();
    }

    private static void initialiserComptes() {
        // Création des comptes
        CompteCourant compteNicolas = new CompteCourant("Nicolas", 2000.00);
        CompteCourant compteJeremie = new CompteCourant("Jérémie", 500.00);
        CompteEpargne compteEpargneNicolas = new CompteEpargne("Nicolas", 2.5);

        // Ajout des opérations initiales
        compteNicolas.crediter(1000);
        compteNicolas.debiter(50);
        compteNicolas.crediter(100);
        compteNicolas.crediter(200);

        compteJeremie.debiter(500);
        compteJeremie.debiter(200);

        compteEpargneNicolas.crediter(200);
        compteEpargneNicolas.crediter(100);

        // Ajout des comptes à la liste
        comptes.add(compteNicolas);
        comptes.add(compteJeremie);
        comptes.add(compteEpargneNicolas);
    }

    private static void afficherMenu() {
        System.out.println("\nMenu :");
        System.out.println("1. Créer un compte courant");
        System.out.println("2. Créer un compte épargne");
        System.out.println("3. Créditer un compte");
        System.out.println("4. Débiter un compte");
        System.out.println("5. Effectuer un virement");
        System.out.println("6. Afficher la liste des comptes");
        System.out.println("7. Trier les comptes");
        System.out.println("8. Quitter");
    }

    private static void creerCompteCourant(Scanner scanner) {
        System.out.print("Nom du propriétaire : ");
        String proprietaire = scanner.nextLine();
        System.out.print("Découvert autorisé : ");
        double decouvert = scanner.nextDouble();
        comptes.add(new CompteCourant(proprietaire, decouvert));
        System.out.println("Compte courant créé avec succès !");
    }

    private static void creerCompteEpargne(Scanner scanner) {
        System.out.print("Nom du propriétaire : ");
        String proprietaire = scanner.nextLine();
        System.out.print("Taux d'abondement (en % et avec une virgule) : ");
        double taux = scanner.nextDouble();
        comptes.add(new CompteEpargne(proprietaire, taux));
        System.out.println("Compte épargne créé avec succès !");
    }

    private static void crediterCompte(Scanner scanner) {
        Compte compte = selectionnerCompte(scanner);
        if (compte != null) {
            System.out.print("Montant à créditer : ");
            double montant = scanner.nextDouble();
            compte.crediter(montant);
            System.out.println("Compte crédité avec succès !");
        }
    }

    private static void debiterCompte(Scanner scanner) {
        Compte compte = selectionnerCompte(scanner);
        if (compte != null) {
            System.out.print("Montant à débiter : ");
            double montant = scanner.nextDouble();
            compte.debiter(montant);
            System.out.println("Compte débité avec succès !");
        }
    }

    private static void effectuerVirement(Scanner scanner) {
        System.out.println("Compte source :");
        Compte source = selectionnerCompte(scanner);
        if (source == null) return;

        System.out.println("Compte destination :");
        Compte destination = selectionnerCompte(scanner);
        if (destination == null) return;

        if (source == destination) {
            System.out.println("Le compte source et le compte destination doivent être différents.");
            return;
        }

        System.out.print("Montant du virement : ");
        double montant = scanner.nextDouble();
        source.virement(montant, destination);
    }


    private static void afficherComptes() {
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte à afficher.");
            return;
        }

        for (Compte compte : comptes) {
            compte.information();
        }
    }

    private static void trierComptes() {
        Collections.sort(comptes);
        System.out.println("Comptes triés par solde !");
    }

    private static Compte selectionnerCompte(Scanner scanner) {
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte disponible.");
            return null;
        }

        System.out.println("Liste des comptes :");
        for (int i = 0; i < comptes.size(); i++) {
            System.out.println((i + 1) + ". " + comptes.get(i).proprietaire);
        }

        System.out.print("Sélectionnez un compte (par numéro) : ");
        int choix = scanner.nextInt();
        if (choix < 1 || choix > comptes.size()) {
            System.out.println("Choix invalide.");
            return null;
        }

        return comptes.get(choix - 1);
    }
}
