package mypackage;
public class PersonnageHxH
{
    private String nom;
    private int vie;
    private int force;
    private int vitesse;
    private int intelligence;
    private String nen;
    private int experience;

    private String capaciteOffensive;
    private String capaciteDefensive;
    private String capaciteEsquive;

    private int cooldownOffensive = 0; // Tour actuel pour la capacité offensive
    private int cooldownDefensive = 0; // Tour actuel pour la capacité défensive
    private int cooldownEsquive = 0; // Tour actuel pour la capacité d'esquive


     // Constructeur de la classe 

     public PersonnageHxH (String nom, int vie, int force, int vitesse, int intelligence, String nen, int experience, 
     String capaciteOffensive, String capaciteDefensive, String capacitéEsquive)
     {
        this.nom = nom;
        this.vie = vie;
        this.force = force;
        this.vitesse = vitesse;
        this.intelligence = intelligence;
        this.nen = nen;
        this.experience = experience;

        this.capaciteOffensive = capaciteOffensive;
        this.capaciteDefensive =capaciteDefensive;
        this.capaciteEsquive = capacitéEsquive;
     }

     // Getters pour acceder aux attributs
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

     public void setVie(int vie) 
     {
         this.vie = vie;
     }
     public String getCapaciteOffensive()
     {
         return capaciteOffensive;
     }
     public String getCapaciteDefensive()
     {
         return capaciteDefensive;
     }
     public String getCapaciteEsquive()
     {
         return capaciteEsquive;
     }
     //Methode afficher info personnage
     public void afficherInfo()
     {
        System.out.println("Nom : " + nom + ", Vie : " + vie + ", Force : " + force + ", Vitesse : " + vitesse + ", Intelligence : " + intelligence + ", Catégorie de Nen : " + nen + ", Exeperience : " + experience);
     }
     public void reductionCooldown()
     {
      if (cooldownOffensive > 0 ) cooldownOffensive--;
      if (cooldownDefensive > 0 ) cooldownDefensive--;
      if (cooldownEsquive > 0) cooldownEsquive--;
     }
