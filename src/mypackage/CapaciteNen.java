package mypackage;

public abstract class CapaciteNen 
{
    protected String nom;
    protected int cooldown;
    protected int tourRestants;

    public CapaciteNen(String nom, int cooldown) 
    {
        this.nom = nom;
        this.cooldown = cooldown;
        this.tourRestants = 0;
    }

    public boolean estDisponible() 
    {
        return tourRestants == 0;
    }

    public void resetCooldown() 
    {
        tourRestants = cooldown;
    }

    // Mise à jour de la signature de la méthode abstraite
    public abstract int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, int degatsRecus, boolean coupCritique);

    public String getNom() 
    {
        return nom;
    }

     // Nouvelle méthode soigner - Par défaut, rend 0 points de vie
     public int soigner(PersonnageHxH personnage) 
    {
        return 0;  // Certaines capacités ne feront rien en termes de soin
    }

    public void decrementerCooldown() 
    {
        if (tourRestants > 0) {
            tourRestants--;
        }
    }

    public void setCooldown(int tours) 
    {
        this.cooldown = tours;
    }

    @Override
    public String toString() 
    {
        return nom + " (Cooldown: " + cooldown + " tours)";
    }
}

