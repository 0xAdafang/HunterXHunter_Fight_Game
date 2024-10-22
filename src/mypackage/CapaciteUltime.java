package mypackage;

import java.util.Random;

public class CapaciteUltime extends CapaciteNen 
{
    private int toursDepuisDebut;
    private double chanceActivation;

    // Constructeur
    public CapaciteUltime(String nom, double chanceActivation) 
    {
        super(nom, 0);  // Pas de cooldown, donc on le met à 0
        this.toursDepuisDebut = 0;
        this.chanceActivation = chanceActivation;
    }

    // Méthode pour déclencher la capacité ultime avec une chance d'activation
    public boolean tenterActivation() 
    {
        if (toursDepuisDebut >= 3) 
        {  // Disponible après 3 tours
            Random rand = new Random();
            if (rand.nextDouble() <= chanceActivation) 
            {
                // Capacité activée
                System.out.println("Capacité ultime " + nom + " activée !");
                toursDepuisDebut = 0;  // Réinitialiser le compteur de tours
                return true;
            } 
            else 
            {
                // Échec de l'activation
                System.out.println("Échec de l'activation de " + nom + ".");
                return false;
            }
        }
        return false;  // Capacité pas encore disponible
    }

    // Méthode propre à CapaciteUltime avec seulement 2 paramètres (attaquant et défenseur)
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur) 
    {
        // Calcul des dégâts : -50% de la vie actuelle du défenseur
        int degats = defenseur.getVie() / 2;
        System.out.println(attaquant.getNom() + " a activé " + nom + " et inflige " + degats + " dégâts !");
        return degats;
    }

    // Implémentation de la méthode héritée avec 4 arguments
    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique) 
    {
        // Calcul des dégâts : -50% de la vie actuelle du défenseur
        int degats = defenseur.getVie() / 2;
        System.out.println(attaquant.getNom() + " a activé " + nom + " et inflige " + degats + " dégâts!");
        return degats;
    }

    // Incrémenter le compteur de tours après chaque tour
    public void incrementerTours() 
    {
        toursDepuisDebut++;
    }

    // Réinitialiser le compteur de tours pour la capacité ultime
    public void resetToursDepuisDebut() 
    {
        this.toursDepuisDebut = 0;
    }

    // Méthode pour obtenir le nombre de tours écoulés depuis le début
    public int getToursDepuisDebut() 
    {
        return toursDepuisDebut;
    }

    // Vérifier si la capacité ultime est disponible (après 3 tours)
    @Override
    public boolean estDisponible() 
    {
        return toursDepuisDebut >= 3;
    }
}

