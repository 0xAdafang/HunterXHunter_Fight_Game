package mypackage;
import java.util.Random;

public class CapaciteUltime extends CapaciteNen implements CapaciteInterface {
    private int tourDepuisDebut;   // Compteur des tours
    private double chanceActivation;  // Chance de 25% d'activer la capacité ultime

    // Constructeur
    public CapaciteUltime (String nom, int toursDepuisDebut, double chanceActivation) {
        super(nom, 0);  // Plus besoin de cooldown, donc on le met à 0
        this.tourDepuisDebut = 0;
        this.chanceActivation = chanceActivation;  // Correction de l'assignation
    }

    // Méthode pour déclencher la capacité ultime
    public boolean tenterActivation() {
        if (tourDepuisDebut >= 3) {
            Random rand = new Random();
            if (rand.nextDouble() <= chanceActivation) {
                // Capacité activée
                System.out.println("Capacité ultime " + nom + " activée !");
                tourDepuisDebut = 0;  // Réinitialiser le compteur de tours
                return true;
            } else {
                // Échec de l'activation
                System.out.println("Échec de l'activation de " + nom + ".");
                return false;
            }
        }
        return false;  // Pas encore possible d'activer
    }

     // Implémentation de la méthode abstraite héritée de CapaciteInterface
     @Override
     public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur) {
         // Calcul des dégâts : -50% de la vie actuelle du défenseur
         int degats = defenseur.getVie() / 2;
         System.out.println(attaquant.getNom() + " a activé " + nom + " et inflige " + degats + " dégâts!");
         return degats;
     }

    // Implémentation de la méthode héritée de CapaciteNen
    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique) {
        // Calcul des dégâts : -50% de la vie actuelle du défenseur
        int degats = defenseur.getVie() / 2;
        System.out.println(attaquant.getNom() + " a activé " + nom + " et inflige " + degats + " dégâts!");
        return degats;
    }


    // Incrémenter le compteur de tours après chaque tour
    public void incrementerTours() 
    {
        tourDepuisDebut++;
    }

    @Override
    public boolean estDisponible() 
    {
        // Disponible après 3 tours
        return tourDepuisDebut >= 3;
    }

    public int getToursDepuisDebut() 
    {
        return tourDepuisDebut;
    }

    
}
