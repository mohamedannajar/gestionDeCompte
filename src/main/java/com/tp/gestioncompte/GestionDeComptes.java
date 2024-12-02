package com.tp.gestioncompte;

import java.util.ArrayList;
import java.util.List;

public class GestionDeComptes {
    private List<Compte> comptes;

    public GestionDeComptes() {
        this.comptes = new ArrayList<>();
    }

    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
    }

    public void afficherComptes() {
        for (Compte compte : comptes) {
            compte.information();
            System.out.println("*******************************************");
        }
    }
}
