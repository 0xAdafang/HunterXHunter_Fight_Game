package mypackage;

import java.util.Random;

public class CapaciteEsquive extends CapaciteNen
{
    public CapaciteEsquive (String nom, int cooldown)
    {
        super(nom, cooldown);
    }

    @Override
    public int activer (PersonnageHxH attaquant, PersonnageHxH defenseur, boolean coupCritique)
    {
        if(nom.equals("Bond"))
        {
            if(Math.random() < 0.25)
            {
                System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                return 0;
            }
        }
        resetCooldown();
        return attaquant.getForce();
    }
    public boolean calculerEsquive (int vitesse)
    {
        Random random = new Random();
        int chanceEsquive = random.nextInt(100);
        int seuilEsquive = 20; // 20% de base

        // Ajuste la probabilité d'esquive selon la vitesse

        if(chanceEsquive < seuilEsquive + vitesse /5)
        {
            System.out.println("Esquive réussie ! ");
            resetCooldown();
            return true; //esquive reussie !
        }
        System.out.println("Esquive raté ! ");
        return false; //esquive manquée
    }
}
