package mypackage;

import java.util.Random;

public class CapaciteEsquive extends CapaciteNen 
{
    public CapaciteEsquive(String nom, int cooldown) 
    {
        super(nom, cooldown);
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique) 
    {
        int degatsReduits = 0; // Dégâts à renvoyer

        switch (nom) {
            case "Bond":
                if (Math.random() < 0.25) {  // 25% de chance d'esquiver avec cette capacité
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0;  // Esquive réussie, pas de dégâts
                }
                break;

            case "Lightning Step":  // Killua
                if (Math.random() < 0.3) { // 30% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Pluie de Cartes":  // Hisoka
                if (Math.random() < 0.2) { // 20% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Activation des Yeux Écarlates":  // Kurapika
                if (Math.random() < 0.15) { // 15% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Réflexe Chirurgical":  // Leorio
                if (Math.random() < 0.2) { // 20% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Disparition Furtive":  // Zeno
                if (Math.random() < 0.35) { // 35% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Esquive Tactique":  // Shalnark
                if (Math.random() < 0.3) { // 30% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Esquive Rapide":  // Knuckle
                if (Math.random() < 0.25) { // 25% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Pas Enchanté":  // Biscuit
                if (Math.random() < 0.2) { // 20% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            // Ajout des autres capacités
            case "Saut Félin":  // Neferpitou
                if (Math.random() < 0.3) { // 30% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Mur de Nen":  // Isaac Netero
                if (Math.random() < 0.25) { // 25% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Armure Physique":  // Uvogin
                if (Math.random() < 0.2) { // 20% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Frappe Aveugle":  // Phinks
                if (Math.random() < 0.25) { // 25% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Stratégie de Fumée":  // Morel
                if (Math.random() < 0.2) { // 20% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Déplacement Furtif":  // Machi
                if (Math.random() < 0.3) { // 30% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            case "Vitesse de l’Ombre":  // Feitan
                if (Math.random() < 0.3) { // 30% de chance d'esquiver
                    System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
                    return 0; // Esquive réussie
                }
                break;

            default:
                System.out.println("Capacité d'esquive inconnue.");
                return attaquant.getForce(); // Retourne la force de l'attaquant si l'esquive échoue
        }

        // Si aucune esquive n'a réussi
        System.out.println(defenseur.getNom() + " a échoué à esquiver l'attaque et subit " + degatsRecus + " dégâts !");
        resetCooldown();  // Réinitialisation du cooldown
        return degatsRecus; // Retourne les dégâts reçus
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
        if (cooldown > 0) 
        {
            cooldown--;
        }
    }

    public boolean tenterEsquive(PersonnageHxH esquiveur, PersonnageHxH adversaire) 
    {
        int intelligenceEsquiveur = esquiveur.getIntelligence();
        int vitesseAdversaire = adversaire.getVitesse();
        int chanceEsquive = intelligenceEsquiveur - vitesseAdversaire;
    
        Random random = new Random();
        int seuil = random.nextInt(100);
    
        boolean esquiveReussie = chanceEsquive > seuil;
    
        System.out.println(esquiveur.getNom() + (esquiveReussie ? " esquive l'attaque !" : " échoue à esquiver."));
        resetCooldown();
        return esquiveReussie;
    }

    @Override
    public String toString() 
    {
        return "Capacité Esquive: " + nom + " (Cooldown: " + cooldown + " tours)";
    }
}