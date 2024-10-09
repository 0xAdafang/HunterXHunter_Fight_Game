package mypackage;

import java.util.Random;

public class Combat {
    private PersonnageHxH joueur;
    private PersonnageHxH adversaire;

    // Constantes pour les choix d'action
    private static final int ATTAQUE = 1;
    private static final int DEFENSE = 2;
    private static final int ESQUIVE = 3;

    public Combat(PersonnageHxH joueur, PersonnageHxH adversaire) {
        this.joueur = joueur;
        this.adversaire = adversaire;
    }

    public void lancerCombat() {
        System.out.println("Début du combat entre " + joueur.getNom() + " et " + adversaire.getNom());

        while (joueur.getVie() > 0 && adversaire.getVie() > 0) {
            int choixJoueur = obtenirChoixAction(joueur); // choix du joueur
            int choixAdversaire = obtenirChoixAction(adversaire);

            // calcul coup critique
            boolean coupCritiqueJoueur = joueur.getCapaciteOffensive().calculerCoupCritique(joueur.getIntelligence(), false);
            boolean coupCritiqueAdversaire = adversaire.getCapaciteDefensive().calculerCoupCritique(adversaire.getIntelligence(), false);

            boolean esquiveJoueur = joueur.getCapaciteEsquive().calculerEsquive(joueur.getVitesse());
            boolean esquiveAdversaire = adversaire.getCapaciteEsquive().calculerEsquive(adversaire.getVitesse());

            executerActionsSimultanees(choixJoueur, choixAdversaire, coupCritiqueJoueur, coupCritiqueAdversaire, esquiveJoueur, esquiveAdversaire);

            // réduction cooldown
            joueur.reductionCooldown();
            adversaire.reductionCooldown();
        }

        afficherResultatCombat();
    }

    private int obtenirChoixAction(PersonnageHxH personnage) {
        Random random = new Random();
        if (personnage == joueur) {
            // Remplace cette ligne par une saisie réelle si nécessaire
            return ATTAQUE; // Choix par défaut pour le joueur
        } else {
            return random.nextInt(3) + 1; // 1: atq 2: def 3: esquive
        }
    }

    private void executerActionsSimultanees(int choixJoueur, int choixAdversaire, boolean coupCritiqueJoueur, boolean coupCritiqueAdversaire, boolean esquiveJoueur, boolean esquiveAdversaire) {
        if (choixJoueur == ESQUIVE) {
            if (!esquiveAdversaire) {
                System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
                return; // Si le joueur esquive, on ne fait rien d'autre
            }
        }

        if (choixJoueur == ATTAQUE && choixAdversaire == ATTAQUE) {
            executerAttaqueSimultanee(coupCritiqueJoueur, coupCritiqueAdversaire, esquiveJoueur, esquiveAdversaire);
        } else if (choixJoueur == DEFENSE && choixAdversaire == ATTAQUE) {
            executerDefense(coupCritiqueAdversaire, esquiveJoueur);
        } else if (choixJoueur == ATTAQUE && choixAdversaire == DEFENSE) {
            executerAttaqueAvecDefense(coupCritiqueJoueur, esquiveAdversaire);
        }
    }

    private void executerAttaqueSimultanee(boolean coupCritiqueJoueur, boolean coupCritiqueAdversaire, boolean esquiveJoueur, boolean esquiveAdversaire) {
        if (!esquiveAdversaire) {
            int degatsJoueur = joueur.getCapaciteOffensive().activer(joueur, adversaire, coupCritiqueJoueur);
            adversaire.setVie(adversaire.getVie() - degatsJoueur);
            System.out.println(adversaire.getNom() + " a maintenant " + adversaire.getVie() + " points de vie.");
        } else {
            System.out.println(adversaire.getNom() + " a esquivé l'attaque de " + joueur.getNom());
        }

        if (!esquiveJoueur) {
            int degatsAdversaire = adversaire.getCapaciteOffensive().activer(adversaire, joueur, coupCritiqueAdversaire);
            joueur.setVie(joueur.getVie() - degatsAdversaire);
            System.out.println(joueur.getNom() + " a maintenant " + joueur.getVie() + " points de vie.");
        } else {
            System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
        }
    }

    public void executerDefense(boolean coupCritiqueAdversaire, boolean esquiveJoueur) {
        if (!esquiveJoueur) {
            int degatsAdversaire = adversaire.getCapaciteOffensive().activer(adversaire, joueur, coupCritiqueAdversaire);
            int degatsReduits = joueur.getCapaciteDefensive().activer(adversaire, joueur, coupCritiqueAdversaire);
            
            // Réduire la vie du joueur en tenant compte de la défense
            joueur.setVie(joueur.getVie() - degatsAdversaire + degatsReduits);
            System.out.println(joueur.getNom() + " a maintenant " + joueur.getVie() + " points de vie.");
        } else {
            System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
        }
    }

    private void executerAttaqueAvecDefense(boolean coupCritiqueJoueur, boolean esquiveAdversaire) {
        if (!esquiveAdversaire) {
            int degatsJoueur = joueur.getCapaciteOffensive().activer(joueur, adversaire, coupCritiqueJoueur);
            adversaire.setVie(adversaire.getVie() - degatsJoueur);
            System.out.println(adversaire.getNom() + " a maintenant " + adversaire.getVie() + " points de vie.");
        } else {
            System.out.println(adversaire.getNom() + " a esquivé l'attaque de " + joueur.getNom());
        }
    }

    private void afficherResultatCombat() {
        if (joueur.getVie() <= 0) {
            System.out.println(joueur.getNom() + " est vaincu !");
        } else {
            System.out.println(adversaire.getNom() + " est vaincu !");
        }
    }
}




