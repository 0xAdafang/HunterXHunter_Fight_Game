package mypackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CapaciteOffensive extends CapaciteNen 
{
    private static final Map<String, Integer> multiplicateurNormal = new HashMap<>();
    private static final Map<String, Integer> multiplicateurCoupCritique = new HashMap<>();
    
    private boolean indisponibleProchainTour; // Nouvelle variable pour indiquer si la capacité est indisponible

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
        this.indisponibleProchainTour = false;  // Initialement, la capacité est disponible
    }

    @Override
    public int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique) 
    {
        if (indisponibleProchainTour) 
        {
            System.out.println(nom + " est indisponible ce tour.");
            return 0; // Aucun dégât si la capacité est indisponible
        }

        int forceAttaquant = attaquant.getForce();
        int intelligenceDefenseur = defenseur.getIntelligence();
        int ecartForceIntelligence = Math.max(0, forceAttaquant - intelligenceDefenseur);

        // Facteur de pondération pour équilibrer les dégâts
        double nerfDegats = 0.25;

        // Calculer les dégâts de base
        int degats = (int) ((forceAttaquant + ecartForceIntelligence) * nerfDegats);

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

        // Marquer la capacité comme indisponible pour le prochain tour
        rendreIndisponiblePourProchainTour();

        return degats;  // Retourne les dégâts pour être appliqués au défenseur
    }

    // Marquer la capacité comme indisponible pour le prochain tour
    public void rendreIndisponiblePourProchainTour() 
    {
        indisponibleProchainTour = true;
    }

    // Vérifier si la capacité est indisponible pour ce tour
    public boolean estIndisponible() 
    {
        return indisponibleProchainTour;
    }

    // Réinitialiser l'indisponibilité après un tour
    public void resetIndisponibilite() 
    {
        indisponibleProchainTour = false;
    }

    // Calcul du coup critique
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

    // Retourne le cooldown actuel (non utilisé ici, mais utile si réactivé)
    public int getCooldown() 
    {
        return cooldown; 
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

    @Override
    public String toString() 
    {
        return "Capacité Offensive: " + nom;
    }
}

