package mypackage;

import java.util.Random;

public class CapaciteDefensive extends CapaciteNen 
{

    public CapaciteDefensive(String nom, int cooldown) 
    {
        super(nom, cooldown);
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique) 
    {
        int intelligenceDefenseur = defenseur.getIntelligence();
        int forceAttaquant = attaquant.getForce();
        int reduction = Math.max(0, intelligenceDefenseur - forceAttaquant / 2);

        int degatsReduits = Math.max(0, degatsRecus - reduction);

        // Gestion des capacités défensives
        switch (nom) 
        {
            case "Bras de fer":  // Gon
                degatsReduits = coupCritique ? 0 : degatsRecus / 2;
                break;
            case "Thunderbolt":  // Killua
                degatsReduits = coupCritique ? degatsRecus / 3 : degatsRecus / 2;
                break;
            case "Texture Trompeuse":  // Hisoka
                degatsReduits = coupCritique ? degatsRecus / 4 : degatsRecus / 2;
                break;
            case "Prison de Nen":  // Kurapika
                degatsReduits = coupCritique ? 0 : degatsRecus / 2;
                break;
            case "Bouclier de Nen":  // Leorio / Netero
                degatsReduits = coupCritique ? degatsRecus / 3 : degatsRecus / 2;
                break;
            case "Nen-Guard":  // Meruem
                degatsReduits = coupCritique ? 0 : degatsRecus / 2;
                break;
            case "Doctor Blythe":  // Neferpitou
                defenseur.ajouterVie(50); // Soigne 50 points de vie
                degatsReduits = degatsRecus / 2;
                break;
            case "Protection Familiale":  // Zeno
                degatsReduits = coupCritique ? 0 : degatsRecus / 2;
                break;
            case "Armure Physique":  // Uvogin
                degatsReduits = coupCritique ? 0 : degatsRecus / 3;
                break;
            case "Force Inouïe":  // Phinks
                degatsReduits = coupCritique ? degatsRecus / 4 : degatsRecus / 2;
                break;
            case "Black Voice":  // Shalnark
                degatsReduits = coupCritique ? degatsRecus / 3 : degatsRecus / 2;
                break;
            case "Barrière de Fils":  // Machi
                degatsReduits = coupCritique ? degatsRecus / 4 : degatsRecus / 2;
                break;
            case "Rising Sun":  // Feitan
                degatsReduits = coupCritique ? 0 : degatsRecus / 3;
                break;
            case "Blocage Instantané":  // Knuckle
                degatsReduits = coupCritique ? 0 : degatsRecus / 2;
                break;
            case "Mur de Fumée":  // Morel
                degatsReduits = coupCritique ? degatsRecus / 2 : 0;  // Annulation totale possible
                break;
            case "Bouclier de Bonbons":  // Biscuit
                degatsReduits = coupCritique ? degatsRecus / 4 : degatsRecus / 2;
                break;
            default:
                System.out.println("Capacité défensive inconnue.");
                return degatsRecus;
        }

        System.out.println(defenseur.getNom() + " utilise " + nom + " pour réduire les dégâts à " + degatsReduits + "!");

        resetCooldown();  // Réinitialisation du cooldown
        return degatsReduits;  // Retourne les dégâts réduits
    }

    public boolean calculerCoupCritique(int intelligence, boolean coupCritiqueInitial) 
    {
        Random random = new Random();
        int chanceCritique = random.nextInt(100);
        int seuilCritique = 10;

        if (chanceCritique < seuilCritique + intelligence / 10) 
        
        {
            return true;
        }
        return coupCritiqueInitial;
    }

    public int getCooldown() 
    {
        return cooldown;
    }

    public void decrementerCooldown() 
    {
        if (cooldown > 0) 
        {
            cooldown--;
        }
    }

    @Override
    public String toString() 
    {
        return "Capacité Défensive: " + nom + " (Cooldown: " + cooldown + " tours)";
    }
}

