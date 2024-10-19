package mypackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BaseDeDonneesPersonnages 
{
    private ArrayList<PersonnageHxH> personnages; 

    // Constructeur
    public BaseDeDonneesPersonnages()
    {
        personnages = new ArrayList<>();
    }

    // Méthode pour charger les persos de la bdd
    public void chargerPersonnagesDuFichier(String fichier)
    {
        try 
        {
            Scanner sc = new Scanner(new File(fichier));
            while (sc.hasNextLine())
            {
                String ligne = sc.nextLine();
                if (ligne.startsWith("#") || ligne.trim().isEmpty())
                {
                    continue; // Ignorer les commentaires et les lignes vides
                }

                String[] donnees = ligne.split(", ");
                if (donnees.length == 11)
                {
                    String nom = donnees[0];
                    int vie = Integer.parseInt(donnees[1]);
                    int force = Integer.parseInt(donnees[2]);
                    int vitesse = Integer.parseInt(donnees[3]);
                    int intelligence = Integer.parseInt(donnees[4]);
                    String nen = donnees[5];
                    int experience = Integer.parseInt(donnees[6]);
                    
                    
                    String strCapaciteOffensive = donnees[7];
                    CapaciteOffensive capaciteOffensive = new CapaciteOffensive(strCapaciteOffensive, 2);
                    String strCapaciteDefensive = donnees[8];
                    CapaciteDefensive capaciteDefensive  = new CapaciteDefensive(strCapaciteDefensive, 2);
                    String strCapaciteEsquive = donnees[9];
                    CapaciteEsquive capaciteEsquive = new CapaciteEsquive(strCapaciteEsquive, 3);
                    String strCapaciteUltime = donnees[10];
                    CapaciteUltime capaciteUltime = new CapaciteUltime(strCapaciteUltime, 3, 0.2);
                    
                    // Validation avant ajout
                    if (validerPersonnage(nom, vie, force, vitesse, intelligence, nen, experience, strCapaciteOffensive, strCapaciteDefensive, strCapaciteEsquive)) 
                    {
                        PersonnageHxH personnage = new PersonnageHxH(nom, vie, force, vitesse, intelligence, nen, experience, capaciteOffensive, capaciteDefensive, capaciteEsquive, capaciteUltime);
                        personnages.add(personnage);
                    }
                }
                else
                {
                    System.out.println("Erreur dans le format de la ligne : " + ligne);
                }           
            }
            sc.close();  
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Fichier non trouvé : " + fichier);
        }
    }

   // Méthode pour afficher les persos chargés
    public void afficherPersonnage()
    {
        for (PersonnageHxH personnage : personnages)  
        {
        System.out.println("Nom: " + personnage.getNom());
        System.out.println("Vie: " + personnage.getVie());
        System.out.println("Force: " + personnage.getForce());
        System.out.println("Vitesse: " + personnage.getVitesse());
        System.out.println("Intelligence: " + personnage.getIntelligence());
        System.out.println("Nen: " + personnage.getNen());
        System.out.println("Expérience: " + personnage.getExperience());
        System.out.println("Capacité Offensive: " + personnage.getCapaciteOffensive());
        System.out.println("Capacité Défensive: " + personnage.getCapaciteDefensive());
        System.out.println("Capacité Esquive: " + personnage.getCapaciteEsquive());
        System.out.println("----------------------------");
        }
    }

    // Méthode pour valider les personnages
    public boolean validerPersonnage(String nom, int vie, int force, int vitesse, int intelligence, String nen, int experience, String capaciteOffensive, String capaciteDefensive, String capaciteEsquive)
    {
        if (vie < 0 || force < 0 || 
            vitesse < 0 || intelligence < 0 || 
            experience < 0)
        {
            System.out.println("Erreur: Les statistiques de " + nom + " sont invalides.");
            return false;
        }

        String categorieNen = nen;
        if (!categorieNen.equalsIgnoreCase("Emission") &&
            !categorieNen.equalsIgnoreCase("Renforcement") &&
            !categorieNen.equalsIgnoreCase("Manipulation") &&
            !categorieNen.equalsIgnoreCase("Matérialisation") &&
            !categorieNen.equalsIgnoreCase("Transformation") &&
            !categorieNen.equalsIgnoreCase("Spécialisation"))
        {
            System.out.println("Erreur: Catégorie Nen invalide pour " + nom + ".");
            return false;
        } 

        return true;
    }

    // Méthode pour rechercher un personnage par nom
    public PersonnageHxH rechercherPersonnageParNom(String nom) 
    {
        for (PersonnageHxH personnage : personnages) 
        {
            if (personnage.getNom().toLowerCase().contains(nom.toLowerCase())) 
            {
                return personnage;
            }
        }
        System.out.println("Personnage avec le nom " + nom + " non trouvé.");
        return null; // Si le personnage n'est pas trouvé
    }

    // Getter pour la liste des personnages
    public ArrayList<PersonnageHxH> getPersonnages()
    {
        return personnages;
    }
}
