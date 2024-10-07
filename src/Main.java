import mypackage.BaseDeDonneesPersonnages;

public class Main 
{
    public static void main(String[] args)
    { 
        BaseDeDonneesPersonnages bddPersonnages = new BaseDeDonneesPersonnages();

        // Charger le fichier depuis la source correcte
        bddPersonnages.chargerPersonnagesDuFichier("src/data/HxHCharacter.txt");

        // Afficher les perso chargés
        bddPersonnages.afficherPersonnage();
    }
}


