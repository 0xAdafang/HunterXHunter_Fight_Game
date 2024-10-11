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

        if (nom.equals("Pierre-feuille-Ciseaux")) 
        {
            degats *= coupCritique ? 3 : 2;
        } else if (nom.equals("Godspeed")) 
        {
            degats *= coupCritique ? 4 : 2;
        }

        System.out.println(attaquant.getNom() + " utilise " + nom + " pour " + degats + " dégâts!");

        resetCooldown();  // Reset cooldown après utilisation
        return degats;  // Retourne les dégâts pour être appliqués au défenseur
    }

    public boolean calculerCoupCritique(int intelligence, boolean coupCritiqueInitial) 
    {
        Random random = new Random();
        int chanceCritique = random.nextInt(100);
        int seuilCritique = 10;  // 10% de base pour un coup critique

        if (chanceCritique < seuilCritique + intelligence / 10) 
        {
            return true;  // Coup critique réussi !
        }
        return coupCritiqueInitial;  // Sinon, conserve la valeur initiale
    }
    public int getCooldown() 
    {
        return cooldown; // Retourne le cooldown actuel
    }

    public void decrementerCooldown()
    {
        if (cooldown > 0) {
            cooldown--;
        }
    }
    @Override
    public String toString() 
    {
    return "Capacité Offensive: " + nom + " (Cooldown: " + cooldown + " tours)";
    }
}
