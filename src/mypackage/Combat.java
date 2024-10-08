package mypackage;

public class Combat 
{
    private PersonnageHxH joueur1;
    private PersonnageHxH joueur2;

    public Combat(PersonnageHxH joueur1, PersonnageHxH joueur2)
    {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public void lancerCombat()
    {
        System.out.println("Début de combat entre " + joueur1.getNom() + " VS " + joueur2.getNom());

        while (joueur1.getVie() > 0 && joueur2.getVie() > 0)
        {
            // joueur 1 attaque
            attaquer(joueur1, joueur2);
            if (joueur2.getVie() <= 0)
            {
                System.out.println(joueur2.getNom() + " est K.O ! ");
                System.out.println(joueur1.getNom() + " l'emporte !");
                break;
            }

            // joueur 2 attaque
            attaquer(joueur2, joueur1);
            if (joueur1.getVie() <= 0)
            {
                System.out.println(joueur1.getNom() + " est K.O ! ");
                System.out.println(joueur2.getNom() + " l'emporte !");
                break;
            }
        }
    }

    // Méthode attaquer définie en dehors de lancerCombat
    private void attaquer(PersonnageHxH attaquant, PersonnageHxH defenseur)
    {
        int degats = attaquant.getForce();// Calcul simple pour l'exemple
        boolean coupCritique = Math.random() <0.15;

        switch (attaquant.getCapaciteOffensive())
        {
            case "Pierre-feuille-Ciseaux":
            degats *= (coupCritique) ? 3 : 2; //degats triplé si coup critique
            System.out.println(attaquant.getNom() + "Utilise utilise Pierre-feuille-Ciseaux !");

            default :
            if (coupCritique)
            {
                degats *= 2; //coup critique double attaque si pas de capacité utilisées
                System.out.println("Coup critique !");
            }
            break;
        }
        
        switch (defenseur.getCapaciteDefensive())
        {
            case "Bras de fer":
            if (coupCritique)
            {
                System.out.println(defenseur.getNom() + " utilise Bras de fer + et annule le coup critique !");
                degats = 0; // annulation Coup critique
            }
            else 
            {
                degats /= 2; //reduction de degats
                System.out.println(defenseur.getNom() + "utilise Bras de fer + pour reduire les degats !");
            }
            break;

            default:
                 break;

        }

        switch (defenseur.getCapaciteEsquive()) {
            case "Bond":
                if (Math.random() < 0.25) //25% d'esquive totale
                {
                    System.out.println(defenseur.getNom() + "utilise Bond et esquive l'attaque");
                    degats = 0; //annulation degats
                }               
                break;
        
            default:
                break;
        }

        //applique les degats finaux sur le personnage
        defenseur.setVie(defenseur.getVie() - degats);

        //affichage du resultat de l'attaque
        if(degats > 0)
        {
            System.out.println(attaquant.getNom() + "attaque " + defenseur.getNom() + "pour " + degats + " dégats !");
        }
        else
        {
            System.out.println(defenseur.getNen() + " a esquivé ou annulé l'attaque ! ");
        }
        

    }
}


