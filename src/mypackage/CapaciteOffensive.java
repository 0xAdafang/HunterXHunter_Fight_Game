package mypackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CapaciteOffensive extends CapaciteNen 
{
    private static final Map<String, Integer> multiplicateurNormal = new HashMap<>();
    private static final Map<String, Integer> multiplicateurCoupCritique = new HashMap<>();

    // Initialisation des multiplicateurs pour chaque capacité
    static 
    {
        multiplicateurNormal.put("Jajanken", 2);
        multiplicateurCoupCritique.put("Jajanken", 3);

        multiplicateurNormal.put("Godspeed", 2);
        multiplicateurCoupCritique.put("Godspeed", 4);

        multiplicateurNormal.put("Bungee Gum", 1);
        multiplicateurCoupCritique.put("Bungee Gum", 2);

        multiplicateurNormal.put("Chaînes de Jugement", 3);
        multiplicateurCoupCritique.put("Chaînes de Jugement", 5);

        multiplicateurNormal.put("Émission de Nen", 2);
        multiplicateurCoupCritique.put("Émission de Nen", 3);

        multiplicateurNormal.put("Sun and Moon", 4);
        multiplicateurCoupCritique.put("Sun and Moon", 6);

        multiplicateurNormal.put("Aura de l’Empereur", 5);
        multiplicateurCoupCritique.put("Aura de l’Empereur", 7);

        multiplicateurNormal.put("Terpsichora", 2);
        multiplicateurCoupCritique.put("Terpsichora", 4);

        multiplicateurNormal.put("Dragon Head", 3);
        multiplicateurCoupCritique.put("Dragon Head", 5);

        multiplicateurNormal.put("Hyakushiki Kannon", 4);
        multiplicateurCoupCritique.put("Hyakushiki Kannon", 6);

        multiplicateurNormal.put("Big Bang Impact", 3);
        multiplicateurCoupCritique.put("Big Bang Impact", 5);

        multiplicateurNormal.put("Ripper Cyclotron", 3);
        multiplicateurCoupCritique.put("Ripper Cyclotron", 4);

        multiplicateurNormal.put("Automate Mode", 2);
        multiplicateurCoupCritique.put("Automate Mode", 3);

        multiplicateurNormal.put("Fils de Renforcement", 1);
        multiplicateurCoupCritique.put("Fils de Renforcement", 2);

        multiplicateurNormal.put("Pain Packer", 4);
        multiplicateurCoupCritique.put("Pain Packer", 6);

        multiplicateurNormal.put("A.P.R", 2);
        multiplicateurCoupCritique.put("A.P.R", 3);

        multiplicateurNormal.put("Smoky Jail", 1);
        multiplicateurCoupCritique.put("Smoky Jail", 2);

        multiplicateurNormal.put("Transformation Ultime", 3);
        multiplicateurCoupCritique.put("Transformation Ultime", 5);
    }

    public CapaciteOffensive(String nom, int cooldown) 
    {
        super(nom, cooldown);
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur,int degatsRecus ,boolean coupCritique) 
    {
    if (cooldown > 0) 
    {
        System.out.println(nom + " n'est pas disponible (Cooldown restant : " + cooldown + " tours).");
        return 0; // Pas de dégâts si la capacité est en cooldown
    }

    int forceAttaquant = attaquant.getForce();
    int intelligenceDefenseur = defenseur.getIntelligence();
    int ecartForceIntelligence = Math.max(0, forceAttaquant - intelligenceDefenseur);

    // Calculer les dégâts de base
    int degats = forceAttaquant + ecartForceIntelligence;

    // Déterminer si c'est un coup critique
    if (coupCritique) 
    {
        // Appliquer les multiplicateurs pour les coups critiques
        degats *= getMultiplicateurCoupCritique(nom);
    } else 
    {
        // Appliquer les multiplicateurs normaux
        degats *= getMultiplicateurNormal(nom);
    }

    System.out.println(attaquant.getNom() + " utilise " + nom + " pour " + degats + " dégâts!");

    decrementerCooldown();  // Décrémente le cooldown après utilisation
    return degats;  // Retourne les dégâts pour être appliqués au défenseur
    }
    public boolean calculerCoupCritique(int intelligence, boolean coupCritiqueInitial) 
    {
        Random random = new Random();
        int chanceCritique = random.nextInt(100);
        int seuilCritique = 10;  // 10% de base pour un coup critique

        if (chanceCritique < seuilCritique + intelligence / 10) {
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
    public String toString() {
        return "Capacité Offensive: " + nom + " (Cooldown: " + cooldown + " tours)";
    }

    // Méthodes pour obtenir les multiplicateurs
    private int getMultiplicateurNormal(String nom) 
    {
        return multiplicateurNormal.getOrDefault(nom, 1); // Retourne 1 si capacité inconnue
    }

    private int getMultiplicateurCoupCritique(String nom) 
    {
        return multiplicateurCoupCritique.getOrDefault(nom, 1); // Retourne 1 si capacité inconnue
    }
}

