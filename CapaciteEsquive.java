package mypackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CapaciteEsquive extends CapaciteNen 
{
    private static final Map<String, Double> chancesEsquive = new HashMap<>();

    // Initialisation statique de la HashMap avec les capacités et leurs chances d'esquive
    static 
    {
        chancesEsquive.put("Bond", 0.25); //Gon
        chancesEsquive.put("Lightning Step", 0.3);   // Killua
        chancesEsquive.put("Pluie de Cartes", 0.2);  // Hisoka
        chancesEsquive.put("Activation des Yeux Écarlates", 0.15);  // Kurapika
        chancesEsquive.put("Réflexe Chirurgical", 0.2);  // Leorio
        chancesEsquive.put("Disparition Furtive", 0.35);  // Zeno
        chancesEsquive.put("Esquive Tactique", 0.3);  // Shalnark
        chancesEsquive.put("Esquive Rapide", 0.25);   // Knuckle
        chancesEsquive.put("Pas Enchanté", 0.2);      // Biscuit
        chancesEsquive.put("Saut Félin", 0.3);        // Neferpitou
        chancesEsquive.put("Mur de Nen", 0.25);       // Isaac Netero
        chancesEsquive.put("Armure Physique", 0.2);   // Uvogin
        chancesEsquive.put("Frappe Aveugle", 0.25);   // Phinks
        chancesEsquive.put("Stratégie de Fumée", 0.2);  // Morel
        chancesEsquive.put("Déplacement Furtif", 0.3); // Machi
        chancesEsquive.put("Vitesse de l’Ombre", 0.3); // Feitan
        chancesEsquive.put("Instinct Royal", 0.3); // Meruem
        chancesEsquive.put("Camouflage d'Ombre", 0.25); //Chrollo
    }

    public CapaciteEsquive(String nom, int cooldown) 
    {
        super(nom, cooldown);
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique) 
    {
        // Récupération de la chance d'esquive associée à la capacité
        double chance = chancesEsquive.getOrDefault(nom, 0.0);

        if (Math.random() < chance) 
        {
            System.out.println(defenseur.getNom() + " utilise " + nom + " et esquive l'attaque !");
            return 0;  // Esquive réussie, pas de dégâts
        }

        // Si l'esquive échoue
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