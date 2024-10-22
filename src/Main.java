import mypackage.BaseDeDonneesPersonnages;
import mypackage.Combat;
import mypackage.PersonnageHxH;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
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
        System.out.println("Le combat commence !");

        // Lancer le combat une fois que le choix des personnages est valide
        if (joueur1 != null && joueur2 != null) 
        {
            Combat combat = new Combat(joueur1, joueur2);
            boolean combatEnCours = true;

            while (combatEnCours) {
                // Tour du joueur - choisir une action
                int actionJoueur = obtenirChoixAction(joueur1);

                // Gérer l'indisponibilité des capacités offensives
                if (joueur1.getCapaciteOffensive().estIndisponible()) 
                {
                    System.out.println(joueur1.getNom() + " ne peut pas utiliser son attaque ce tour-ci !");
                } 
                else 
                {
                    if (actionJoueur == 4)
                    {
                        System.out.println(joueur1.getNom() + " utilise la Capacité Ultime: " + joueur1.getCapaciteUltime().getNom());
                        int degatsUltime = joueur1.getCapaciteUltime().activer(joueur1, joueur2);
                        joueur2.setVie(joueur2.getVie() - degatsUltime);
                        System.out.println(joueur2.getNom() + " a pris " + degatsUltime + " dégâts.");
                        
                        // Réinitialiser le compteur de tours de la capacité ultime après utilisation
                        joueur1.getCapaciteUltime().resetToursDepuisDebut();
                    } else {
                        System.out.println(joueur1.getNom() + " utilise " + actionToString(actionJoueur) + ": " + joueur1.getCapacites().get(actionJoueur - 1));
                    }
                }

                // Tour de l'ordinateur - choisir une action aléatoire
                int actionOrdinateur = choisirActionAleatoire(joueur2);

                // Gérer l'indisponibilité des capacités offensives
                if (joueur2.getCapaciteOffensive().estIndisponible()) 
                {
                    System.out.println(joueur2.getNom() + " ne peut pas utiliser son attaque ce tour-ci !");
                } else {
                    if (actionOrdinateur == 4) 
                    {
                        System.out.println(joueur2.getNom() + " utilise la Capacité Ultime: " + joueur2.getCapaciteUltime().getNom());
                        int degatsUltime = joueur2.getCapaciteUltime().activer(joueur2, joueur1);
                        joueur1.setVie(joueur1.getVie() - degatsUltime);
                        System.out.println(joueur1.getNom() + " a pris " + degatsUltime + " dégâts.");
                        
                        // Réinitialiser le compteur de tours de la capacité ultime après utilisation
                        joueur2.getCapaciteUltime().resetToursDepuisDebut();
                    } else {
                        System.out.println(joueur2.getNom() + " utilise " + actionToString(actionOrdinateur) + ": " + joueur2.getCapacites().get(actionOrdinateur - 1));
                    }
                }

                // Lancer un tour de combat en fonction des choix établis
                combatEnCours = combat.lancerTour(actionJoueur, actionOrdinateur);

                System.out.println("\n--- État des personnages après ce tour ---");
                System.out.println(joueur1.getEtat());
                System.out.println(joueur2.getEtat());

                if (!combatEnCours) {
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

        ArrayList<String> capacites = personnage.getCapacites();

        for (int i = 0; i < capacites.size(); i++) {
            String capacite = capacites.get(i);
            String action = (i == 0) ? "Attaquer" : (i == 1) ? "Defendre" : "Esquiver";
            System.out.println((i + 1) + ": " + action + " (" + capacite + ")");
        }

        // Afficher la disponibilité de la capacité ultime
        int toursRestants = 3 - personnage.getCapaciteUltime().getToursDepuisDebut();
        if (toursRestants <= 0) {
            System.out.println("4: Utiliser Capacité Ultime (" + personnage.getCapaciteUltime().getNom() + ")");
        } else {
            System.out.println("Capacité Ultime disponible dans " + toursRestants + " tour(s)");
        }

        int choix = scanner.nextInt();

        while (choix < 1 || (choix > 3 && !(choix == 4 && personnage.getCapaciteUltime().estDisponible()))) 
        {
            System.out.println("Veuillez choisir une option valide (1-3 ou 4 si capacité ultime disponible).");
            choix = scanner.nextInt();
        }

        return choix;
    }

    public static int choisirActionAleatoire(PersonnageHxH personnage) 
    {
        Random random = new Random();

        if (personnage.getCapaciteUltime().estDisponible()) 
        {
            return random.nextInt(4) + 1; // Retourne un nombre entre 1 et 4
        } 
        else 
        {
            return random.nextInt(3) + 1; // Retourne un nombre entre 1 et 3 (attaque, défense, esquive)
        }
    }


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

