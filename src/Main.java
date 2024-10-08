import mypackage.BaseDeDonneesPersonnages;
import mypackage.Combat;
import mypackage.PersonnageHxH;

public class Main 
{
    public static void main(String[] args)
    { 
        BaseDeDonneesPersonnages bddPersonnages = new BaseDeDonneesPersonnages();

        // Charger le fichier depuis la source correcte
        bddPersonnages.chargerPersonnagesDuFichier("src/data/HxHCharacter.txt");

        // Afficher les personnages chargés
        bddPersonnages.afficherPersonnage();

        // Rechercher un personnage par nom
        String nomRecherche = "Gon";
        PersonnageHxH personnageTrouve = bddPersonnages.rechercherPersonnageParNom(nomRecherche);

        if (personnageTrouve != null)
        {
            System.out.println("Personnage trouvé : " + personnageTrouve.getNom());
            System.out.println("Statistiques : ");
            System.out.println("Vie: " + personnageTrouve.getVie());
            System.out.println("Force: " + personnageTrouve.getForce());
            System.out.println("Vitesse: " + personnageTrouve.getVitesse());
            System.out.println("Intelligence: " + personnageTrouve.getIntelligence());
            System.out.println("Nen: " + personnageTrouve.getNen());
            System.out.println("Expérience: " + personnageTrouve.getExperience());
        }
        else 
        {
            System.out.println("Personnage " + nomRecherche + " non trouvé.");
        }

        PersonnageHxH joueur1 = bddPersonnages.rechercherPersonnageParNom("Gon");
        PersonnageHxH joueur2 = bddPersonnages.rechercherPersonnageParNom("Hisoka");

        if (joueur1 != null && joueur2 != null )
        {
            Combat combat = new Combat (joueur1, joueur2);
            combat.lancerCombat();

        }
    }
}



