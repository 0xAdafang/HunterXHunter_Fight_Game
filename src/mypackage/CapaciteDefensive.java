package mypackage;

import java.util.Random;

public class CapaciteDefensive extends CapaciteNen 
{
    public CapaciteDefensive(String nom, int cooldown) 
    {
        super(nom, cooldown);
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, boolean coupCritique) 
    {
        int degatsReduits = attaquant.getForce();

        if (nom.equals("Bras de fer")) 
        {
            if (coupCritique) 
            {
                System.out.println(defenseur.getNom() + " utilise " + nom + " et annule le coup critique !");
                degatsReduits = 0;  // Annule complètement les dégâts critiques
            } else 
            {
                degatsReduits /= 2;  // Réduit les dégâts de moitié
                System.out.println(defenseur.getNom() + " utilise " + nom + " pour réduire les dégâts !");
            }
        }

        resetCooldown();  // Reset cooldown après utilisation
        return degatsReduits;  // Retourne les dégâts réduits pour le calcul final
    }

    public boolean calculerCoupCritique(int intelligence, boolean coupCritiqueInitial) 
    {
        Random random = new Random();
        int chanceCritique = random.nextInt(100);
        int seuilCritique = 10;  // 10% de base

        if (chanceCritique < seuilCritique + intelligence / 10) 
        {
            return true;  // Coup critique réussi
        }
        return coupCritiqueInitial;  // Sinon, conserve la valeur initiale
    }
    public int getCooldown() 
    {
        return cooldown; // Retourne le cooldown actuel
    }

    public void decrementerCooldown() 
    {
        if (cooldown > 0) 
        {
            cooldown--;
        }
    }
}

