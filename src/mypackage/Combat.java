package mypackage;

public class Combat 
{
    private PersonnageHxH joueur;
    private PersonnageHxH adversaire;

    // Constantes pour les choix d'action
    private static final int ATTAQUE = 1;
    private static final int DEFENSE = 2;
    private static final int ESQUIVE = 3;

    public Combat(PersonnageHxH joueur, PersonnageHxH adversaire) 
    {
        this.joueur = joueur;
        this.adversaire = adversaire;
    }

    public boolean lancerTour(int choixJoueur, int choixAdversaire) 
    {
        // Calcul des coups critiques pour chaque joueur
        boolean coupCritiqueJoueur = joueur.getCapaciteOffensive().calculerCoupCritique(joueur.getIntelligence(), false);
        boolean coupCritiqueAdversaire = adversaire.getCapaciteOffensive().calculerCoupCritique(adversaire.getIntelligence(), false);

        // Calcul des esquives pour chaque joueur
        boolean esquiveJoueur = joueur.getCapaciteEsquive().calculerEsquive(joueur.getVitesse());
        boolean esquiveAdversaire = adversaire.getCapaciteEsquive().calculerEsquive(adversaire.getVitesse());

        // Exécution des actions choisies par les deux personnages
        executerActionsSimultanees(choixJoueur, choixAdversaire, coupCritiqueJoueur, coupCritiqueAdversaire, esquiveJoueur, esquiveAdversaire);

        // Réduction des cooldowns pour chaque personnage après chaque tour
        joueur.reductionCooldown();
        adversaire.reductionCooldown();

        //chance d'annuler un cooldown par l'experience
        chanceAnnulerCooldown(joueur);
        chanceAnnulerCooldown(adversaire);

        // Vérification si un des personnages est KO
        return joueur.getVie() > 0 && adversaire.getVie() > 0;
    }

    // Méthode pour gérer les actions simultanées des deux personnages lors d'un tour
    private void executerActionsSimultanees(int choixJoueur, int choixAdversaire, boolean coupCritiqueJoueur, boolean coupCritiqueAdversaire, boolean esquiveJoueur, boolean esquiveAdversaire) 
    {
        // Si le joueur choisit d'esquiver
        if (choixJoueur == ESQUIVE) 
        {
            if (!esquiveAdversaire) 
            {
                System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
                return; // Si l'adversaire n'a pas esquivé, on arrête l'action du joueur
            }
        }

        // Si les deux personnages attaquent simultanément
        if (choixJoueur == ATTAQUE && choixAdversaire == ATTAQUE) 
        {
            executerAttaqueSimultanee(coupCritiqueJoueur, coupCritiqueAdversaire, esquiveJoueur, esquiveAdversaire);
        }
        // Si le joueur se défend et l'adversaire attaque
        else if (choixJoueur == DEFENSE && choixAdversaire == ATTAQUE) 
        {
            executerDefense(coupCritiqueAdversaire, esquiveJoueur);
        }
        // Si le joueur attaque et l'adversaire se défend
        else if (choixJoueur == ATTAQUE && choixAdversaire == DEFENSE) 
        {
            executerAttaqueAvecDefense(coupCritiqueJoueur, esquiveAdversaire);
        }
    }

    // Méthode pour gérer les attaques simultanées entre les deux personnages
    private void executerAttaqueSimultanee(boolean coupCritiqueJoueur, boolean coupCritiqueAdversaire, boolean esquiveJoueur, boolean esquiveAdversaire) 
    {
        int degatsJoueur = 0;
        int degatsAdversaire = 0;

        // Si l'adversaire n'a pas esquivé l'attaque
        if (!esquiveAdversaire) 
        {
            degatsJoueur = joueur.getCapaciteOffensive().activer(joueur, adversaire, 0, coupCritiqueJoueur);
            adversaire.setVie(adversaire.getVie() - degatsJoueur);
            System.out.println(adversaire.getNom() + " a maintenant " + adversaire.getVie() + " points de vie.");
        } 
        else 
        {
            System.out.println(adversaire.getNom() + " a esquivé l'attaque de " + joueur.getNom());
        }

        // Si le joueur n'a pas esquivé l'attaque de l'adversaire
        if (!esquiveJoueur) 
        {
            degatsAdversaire = adversaire.getCapaciteOffensive().activer(adversaire, joueur, 0, coupCritiqueAdversaire);
            joueur.setVie(joueur.getVie() - degatsAdversaire);
            System.out.println(joueur.getNom() + " a maintenant " + joueur.getVie() + " points de vie.");
        } 
        else 
        {
            System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
        }
    }

    // Méthode pour gérer la défense du joueur contre une attaque de l'adversaire
    public void executerDefense(boolean coupCritiqueAdversaire, boolean esquiveJoueur) 
    {
        if (!esquiveJoueur) 
        {
            int degatsAdversaire = adversaire.getCapaciteOffensive().activer(adversaire, joueur, 0, coupCritiqueAdversaire);
            int degatsReduits = joueur.getCapaciteDefensive().activer(adversaire, joueur, 0, coupCritiqueAdversaire);

            // Réduire la vie du joueur en tenant compte de sa capacité défensive
            joueur.setVie(joueur.getVie() - (degatsAdversaire - degatsReduits));
            System.out.println(joueur.getNom() + " a maintenant " + joueur.getVie() + " points de vie.");
        } 
        else 
        {
            System.out.println(joueur.getNom() + " a esquivé l'attaque de " + adversaire.getNom());
        }
    }

    // Méthode pour gérer une attaque contre un adversaire qui se défend
    private void executerAttaqueAvecDefense(boolean coupCritiqueJoueur, boolean esquiveAdversaire) 
    {
        if (!esquiveAdversaire) 
        {
            int degatsJoueur = joueur.getCapaciteOffensive().activer(joueur, adversaire, 0, coupCritiqueJoueur);
            adversaire.setVie(adversaire.getVie() - degatsJoueur);
            System.out.println(adversaire.getNom() + " a maintenant " + adversaire.getVie() + " points de vie.");
        } 
        else 
        {
            System.out.println(adversaire.getNom() + " a esquivé l'attaque de " + joueur.getNom());
        }
    }

    private void chanceAnnulerCooldown (PersonnageHxH personnage)
    {
        int experience = personnage.getExperience();
        double chance = experience / 5000.0;

        if (Math.random() < chance)
        {
            personnage.annulerCooldown();
            System.out.println(personnage.getNom() + " a réussi à annuler un cooldown !");
        }
    }

    // Méthode pour afficher le résultat final du combat
    public void afficherResultatCombat() 
    {
        if (joueur.getVie() <= 0) 
        {
            System.out.println(joueur.getNom() + " est vaincu !");
        } 
        else 
        {
            System.out.println(adversaire.getNom() + " est vaincu !");
        }
    }
}
