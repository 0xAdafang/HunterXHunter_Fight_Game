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
                String nom = donnees[0];
                int vie = Integer.parseInt(donnees[1]);
                int force = Integer.parseInt(donnees[2]);
                int vitesse = Integer.parseInt(donnees[3]);
                int intelligence = Integer.parseInt(donnees[4]);
                String nen = donnees[5];
                int experience = Integer.parseInt(donnees[6]);

                // Créer un perso et l'ajouter à la liste
                PersonnageHxH personnage = new PersonnageHxH(nom, vie, force, vitesse, intelligence, nen, experience);
                personnages.add(personnage);  
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
            personnage.afficherInfo();
        }
    }

    // Getters
    public ArrayList<PersonnageHxH> getPersonnages()
    {
        return personnages;
    }
}
