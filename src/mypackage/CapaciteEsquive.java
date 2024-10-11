package mypackage;

import java.util.Random;

public class CapaciteEsquive extends CapaciteNen 
{
    public CapaciteEsquive(String nom, int cooldown) 
    {
        super(nom, cooldown);
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, boolean coupCritique) 
    {
        if (nom.equals("Bond")) 
        {
            if (Math.random() < 0.25) {  // 25% de chance d'esquiver avec cette capacité
                System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                return 0;  // Esquive réussie, pas de dégâts
            }
        }

        resetCooldown();  // Reset cooldown après utilisation
        return attaquant.getForce();  // Retourne la force de l'attaquant si l'esquive échoue
    }

    public boolean calculerEsquive(int vitesse) 
    {
        Random random = new Random();
        int chanceEsquive = random.nextInt(100);
        int seuilEsquive = 10;  // Ajusté à 10% de base

        if (chanceEsquive < seuilEsquive + vitesse / 5) 
        {
            System.out.println("Esquive réussie !");
            resetCooldown();  // Réinitialise le cooldown si l'esquive réussit
            return true;
        }

        System.out.println("Esquive ratée !");
        return false;  // Esquive échouée
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
    return "Capacité Esquive: " + nom + " (Cooldown: " + cooldown + " tours)";
    }
}
