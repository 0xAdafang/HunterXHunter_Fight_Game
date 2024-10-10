package mypackage;

import java.util.Random;

public class CapaciteOffensive extends CapaciteNen
{
    public CapaciteOffensive(String nom, int cooldown)
    {
        super(nom, cooldown);
    }
    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, boolean coupCritique)
    {
        int degats = attaquant.getForce();

        if(nom.equals("Pierre-feuille-Ciseaux"))
        {
            degats *= coupCritique ? 3 : 2;
        }
        else if(nom.equals("Godspeed"))
        {
            degats *= coupCritique ? 4 : 2;
        }
        System.out.println(attaquant.getNom() + "utilise " + nom + "pour " + degats + " dégâts!");
        resetCooldown();
        return degats;
    }

    public boolean calculerCoupCritique (int intelligence, boolean coupCritiqueInitial)
    {
         // Ajout d'une probabilité supplémentaire de coup critique basée sur l'intelligence (ex. +1% par point d'intelligence)
        Random random = new Random();
        int chanceCritique = random.nextInt(100);
        int SeuilCritique = 10; //10% de base

        // Ajuste la probabilité selon l'intelligence du personnage
        if (chanceCritique < SeuilCritique + intelligence / 10)
        {
            return true; //coup critique reussi !
        }
        return coupCritiqueInitial; //Sinon, conserve la valeur initiale



    }
}
