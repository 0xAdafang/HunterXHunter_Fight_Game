package mypackage;

import java.util.Random;

public class CapaciteDefensive extends CapaciteNen
{
    public CapaciteDefensive (String nom, int cooldown)
    {
        super (nom, cooldown);
    }

    @Override
    public int activer (PersonnageHxH attaquant, PersonnageHxH defenseur, boolean coupCritique)
    {
        int degatsReduits = attaquant.getForce();

        if(nom.equals("Bras de fer"))
        {
            if(coupCritique)
            {
                System.out.println(defenseur.getNom() + "utilise " + nom + " et annule le coup critique !");
                degatsReduits = 0;
            }
            else
            {
                degatsReduits /=2;
                System.out.println(defenseur.getNom() + " utilise " + nom + " pour réduire les dégâts !");
            }
        }
        resetCooldown();
        return degatsReduits;
    }
     public boolean calculerCoupCritique(int intelligence, boolean coupCritiqueInitial)
    {
        Random random = new Random();
        int chanceCritique = random.nextInt(100);
        int seuilCritique = 10; // 10% de base

        // Ajuste la probabilité selon l'intelligence
        if (chanceCritique < seuilCritique + intelligence / 10) {
            return true;
        }
        return coupCritiqueInitial;
    }
}
