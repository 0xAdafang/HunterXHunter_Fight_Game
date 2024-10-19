package mypackage;
import java.util.ArrayList;

public class PersonnageHxH 
{
    private String nom;
    private int vie;
    private int force;
    private int vitesse;
    private int intelligence;
    private String nen;
    private int experience;

    private CapaciteOffensive capaciteOffensive;
    private CapaciteDefensive capaciteDefensive;
    private CapaciteEsquive capaciteEsquive;

    private CapaciteUltime capaciteUltime;

    // Constructeur de la classe
    public PersonnageHxH(String nom, int vie, int force, int vitesse, int intelligence, String nen, int experience, 
                        CapaciteOffensive capaciteOffensive, CapaciteDefensive capaciteDefensive, CapaciteEsquive capaciteEsquive, CapaciteUltime capaciteUltime) 
    {
        this.nom = nom;
        this.vie = vie;
        this.force = force;
        this.vitesse = vitesse;
        this.intelligence = intelligence;
        this.nen = nen;
        this.experience = experience;

        this.capaciteOffensive = capaciteOffensive;
        this.capaciteDefensive = capaciteDefensive;
        this.capaciteEsquive = capaciteEsquive;

        this.capaciteUltime = capaciteUltime;
    }

   

   // Getters pour accéder aux attributs
    public String getNom() 
    {
        return nom;
    }

    public int getVie() 
    {
        return vie;
    }

    public int getForce() 
    {
        return force;
    }

    public int getVitesse() 
    {
        return vitesse;
    }

    public int getIntelligence() 
    {
        return intelligence;
    }

    public String getNen() 
    {
        return nen;
    }

    public int getExperience() 
    {
        return experience;
    }

    public void setVie (int newVie)
    {
        if (newVie < 0)
        {
            this.vie = 0;
        }
        else
        {
            this.vie = newVie;
        }
    }

    public CapaciteOffensive getCapaciteOffensive() 
    {
        return capaciteOffensive;
    }

    public CapaciteDefensive getCapaciteDefensive() 
    {
        return capaciteDefensive;
    }

    public CapaciteEsquive getCapaciteEsquive() 
    {
        return capaciteEsquive;
    }

    public CapaciteUltime getCapaciteUltime()
    {
        return capaciteUltime;
    }

    public void ajouterVie(int point)
    {
        this.vie = Math.min(this.vie + point, 1000);
    }

    public ArrayList<String> getCapacites() 
    {
        ArrayList<String> capacites = new ArrayList<>();
        capacites.add(capaciteOffensive.getNom()); // Assurez-vous que CapaciteOffensive a une méthode getNom()
        capacites.add(capaciteDefensive.getNom()); // Assurez-vous que CapaciteDefensive a une méthode getNom()
        capacites.add(capaciteEsquive.getNom()); // Assurez-vous que CapaciteEsquive a une méthode getNom()
        return capacites;
    }


    // Méthode pour afficher les informations du personnage
    public void afficherInfo() 
    {
        System.out.println("Nom : " + nom + ", Vie : " + vie + ", Force : " + force + ", Vitesse : " + vitesse + 
                           ", Intelligence : " + intelligence + ", Catégorie de Nen : " + nen + ", Expérience : " + experience);
    }

    public String getEtat() 
    {
        return "Points de vie : " + vie + 
               ", Cooldown Capacités : " +
               "Attaque - " + capaciteOffensive.getCooldown() + ", " +
               "Défense - " + capaciteDefensive.getCooldown() + ", " +
               "Esquive - " + capaciteEsquive.getCooldown();
    }

    public void annulerCooldown() 
    {
        if (capaciteOffensive != null) capaciteOffensive.resetCooldown();
        if (capaciteDefensive != null) capaciteDefensive.resetCooldown();
        if (capaciteEsquive != null) capaciteEsquive.resetCooldown();
    }

    // Méthode pour réduire les cooldowns
    public void reductionCooldown() 
    {
        if (capaciteOffensive != null) capaciteOffensive.decrementerCooldown();
        if (capaciteDefensive != null) capaciteDefensive.decrementerCooldown();
        if (capaciteEsquive != null) capaciteEsquive.decrementerCooldown();
    }
}
