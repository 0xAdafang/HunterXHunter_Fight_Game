package mypackage;

public class Combat 
{
    private PersonnageHxH joueur;
    private PersonnageHxH adversaire;

    // Constantes pour les choix d'action
    private static final int ATTAQUE = 1;
    private static final int DEFENSE = 2;
    private static final int ESQUIVE = 3;
    private static final int ULTIME = 4;

    public Combat(PersonnageHxH joueur, PersonnageHxH adversaire) 
    {
        this.joueur = joueur;
        this.adversaire = adversaire;
    }

    public boolean lancerTour(int choixJoueur, int choixAdversaire) 
    {
        // Gérer l'esquive du joueur et de l'adversaire
        boolean esquiveJoueur = (choixJoueur == ESQUIVE) && joueur.getCapaciteEsquive().calculerEsquive(joueur.getVitesse());
        boolean esquiveAdversaire = (choixAdversaire == ESQUIVE) && adversaire.getCapaciteEsquive().calculerEsquive(adversaire.getVitesse());

        // Gestion des attaques
        if (choixJoueur == ATTAQUE && !esquiveAdversaire) 
        {
            int degatsJoueur = joueur.getCapaciteOffensive().activer(joueur, adversaire, 0, false);
            adversaire.setVie(adversaire.getVie() - degatsJoueur);
            System.out.println(adversaire.getNom() + " a pris " + degatsJoueur + " dégâts.");
        } else if (esquiveAdversaire) 
        {
            System.out.println(adversaire.getNom() + " a esquivé l'attaque de " + joueur.getNom());
        }

        if (choixAdversaire == ATTAQUE && !esquiveJoueur) 
        {
            int degatsAdversaire = adversaire.getCapaciteOffensive().activer(adversaire, joueur, 0, false);
            joueur.setVie(joueur.getVie() - degatsAdversaire);
            System.out.println(joueur.getNom() + " a pris " + degatsAdversaire + " dégâts.");
        } else if (esquiveJoueur) 
        {
            System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
        }

        // Gestion de la défense du joueur et de l'adversaire
        if (choixJoueur == DEFENSE && choixAdversaire == ATTAQUE) 
        {
            int degatsAdversaire = adversaire.getCapaciteOffensive().activer(adversaire, joueur, 0, false);
            int degatsReduits = joueur.getCapaciteDefensive().activer(adversaire, joueur, degatsAdversaire, false);
            joueur.setVie(joueur.getVie() - degatsReduits);
            System.out.println(joueur.getNom() + " s'est défendu et a pris " + degatsReduits + " dégâts.");
        }

        if (choixAdversaire == DEFENSE && choixJoueur == ATTAQUE) 
        {
            int degatsJoueur = joueur.getCapaciteOffensive().activer(joueur, adversaire, 0, false);
            int degatsReduits = adversaire.getCapaciteDefensive().activer(joueur, adversaire, degatsJoueur, false);
            adversaire.setVie(adversaire.getVie() - degatsReduits);
            System.out.println(adversaire.getNom() + " s'est défendu et a pris " + degatsReduits + " dégâts.");
        }

        // Gestion de la capacité ultime
        if (choixJoueur == ULTIME && joueur.getCapaciteUltime().estDisponible()) 
        {
            int degatsUltime = joueur.getCapaciteUltime().activer(joueur, adversaire);
            adversaire.setVie(adversaire.getVie() - degatsUltime);
            System.out.println(joueur.getNom() + " a utilisé sa capacité ultime et inflige " + degatsUltime + " dégâts.");
            joueur.getCapaciteUltime().resetToursDepuisDebut(); // Réinitialiser après utilisation
        }

        if (choixAdversaire == ULTIME && adversaire.getCapaciteUltime().estDisponible()) 
        {
            int degatsUltimeAdversaire = adversaire.getCapaciteUltime().activer(adversaire, joueur);
            joueur.setVie(joueur.getVie() - degatsUltimeAdversaire);
            System.out.println(adversaire.getNom() + " a utilisé sa capacité ultime et inflige " + degatsUltimeAdversaire + " dégâts.");
            adversaire.getCapaciteUltime().resetToursDepuisDebut(); // Réinitialiser après utilisation
        }

        // Incrémenter les tours pour chaque capacité ultime
        joueur.getCapaciteUltime().incrementerTours();
        adversaire.getCapaciteUltime().incrementerTours();

        // Vérifier si un des personnages est K.O.
        return joueur.getVie() > 0 && adversaire.getVie() > 0;
    }

    // Méthode pour afficher le résultat final du combat
    public void afficherResultatCombat() 
    {
        if (joueur.getVie() <= 0) 
        {
            System.out.println(joueur.getNom() + " est vaincu !");
        } else {
            System.out.println(adversaire.getNom() + " est vaincu !");
        }
    }
}

