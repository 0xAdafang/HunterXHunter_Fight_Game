import mypackage.BaseDeDonneesPersonnages;
import mypackage.Combat;
import mypackage.PersonnageHxH;

import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    { 
        BaseDeDonneesPersonnages bddPersonnages = new BaseDeDonneesPersonnages();

        // Charger le fichier depuis la source correcte
        bddPersonnages.chargerPersonnagesDuFichier("src/data/HxHCharacter.txt");

        // Afficher les personnages chargés
        bddPersonnages.afficherPersonnage();

        // Choisir personnage pour le joueur et l'ordinateur
        PersonnageHxH joueur1 = choisirPersonnageJoueur(bddPersonnages.getPersonnages(), "Choisissez votre personnage :");
        PersonnageHxH joueur2 = choisirPersonnageJoueur(bddPersonnages.getPersonnages(), "Choisissez le personnage de l'ordinateur :");

        System.out.println(joueur1.getNom() + " VS " + joueur2.getNom());

        // Lancer le combat une fois que le choix des personnages est valide
        if (joueur1 != null && joueur2 != null) 
        {
            Combat combat = new Combat(joueur1, joueur2);
            
            boolean combatEnCours = true;

            while (combatEnCours) 
            {
                // Tour du joueur - choisir une action
                int actionJoueur = obtenirChoixAction(joueur1);
                System.out.println(joueur1.getNom() + " utilise " + actionToString(actionJoueur) + ": " + joueur1.getCapacites().get(actionJoueur - 1));

                // Tour de l'ordinateur - choisir une action aléatoire
                int actionOrdinateur = choisirActionAleatoire();
                System.out.println(joueur2.getNom() + " utilise " + actionToString(actionOrdinateur) + ": " + joueur2.getCapacites().get(actionOrdinateur - 1));

                // Lancer un tour de combat en fonction des choix établis
                combatEnCours = combat.lancerTour(actionJoueur, actionOrdinateur);

                if (!combatEnCours) 
                {
                    System.out.println("K.O !");
                    combat.afficherResultatCombat();
                }
            }
        } 
        else 
        {
            System.out.println("Impossible de lancer le combat, un ou plusieurs personnages sont introuvables.");
        }
    }

    // Méthode pour permettre au joueur de choisir un personnage, avec un message personnalisé
    public static PersonnageHxH choisirPersonnageJoueur(ArrayList<PersonnageHxH> personnages, String message) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(message);

        // Afficher tous les personnages disponibles
        for (int i = 0; i < personnages.size(); i++) 
        {
            System.out.println((i + 1) + ". " + personnages.get(i).getNom());
        }

        // Demander au joueur de choisir un personnage par numéro
        int choix = -1;
        while (choix < 1 || choix > personnages.size()) 
        {
            System.out.print("Entrez le numéro du personnage (1-" + personnages.size() + "): ");
            choix = sc.nextInt();
        }

        // Retourner le personnage choisi
        return personnages.get(choix - 1);
    }

    // Méthode pour choisir l'action du joueur (attaquer, défendre, esquiver)
    public static int obtenirChoixAction(PersonnageHxH personnage) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une action pour " + personnage.getNom() + ":");

        System.out.println(personnage.getEtat());

        // Récupérer les capacités du personnage
        ArrayList<String> capacites = personnage.getCapacites(); // Assurez-vous que cette méthode est définie dans PersonnageHxH

        // Afficher les capacités avec les types d'actions
        for (int i = 0; i < capacites.size(); i++) 
        {
            String capacite = capacites.get(i);
            String action = "";
            if (i == 0) {
                action = "Attaquer"; // Première capacité est l'attaque
            } else if (i == 1) {
                action = "Défendre"; // Deuxième capacité est la défense
            } else if (i == 2) {
                action = "Esquiver"; // Troisième capacité est l'esquive
            }
            System.out.println((i + 1) + ": " + action + " (" + capacite + ")");
        }

        int choix = scanner.nextInt();
        while (choix < 1 || choix > capacites.size()) 
        {
            System.out.println("Veuillez choisir une option valide (1-" + capacites.size() + ").");
            choix = scanner.nextInt();
        }
        return choix; // Retourne l'indice de la capacité choisie
    }

    // Méthode pour choisir une action aléatoire pour l'ordinateur
    public static int choisirActionAleatoire() 
    {
        return (int) (Math.random() * 3) + 1; // Retourne un nombre entre 1 et 3
    }

    // Méthode utilitaire pour afficher une action en texte
    public static String actionToString(int action) 
    {
        switch (action) 
        {
            case 1:
                return "Attaquer";
            case 2:
                return "Défendre";
            case 3:
                return "Esquiver";
            default:
                return "Action inconnue";
        }
    }
}
