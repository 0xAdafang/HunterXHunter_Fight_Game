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

     // Constructeur de la classe 

     public PersonnageHxH (String nom, int vie, int force, int vitesse, int intelligence, String nen, int experience)
     {
        this.nom = nom;
        this.vie = vie;
        this.force = force;
        this.vitesse = vitesse;
        this.intelligence = intelligence;
        this.nen = nen;
        this.experience = experience;
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
     //Methode afficher info personnage
     public void afficherInfo()
     {
        System.out.println("Nom : " + nom + ", Vie : " + vie + ", Force : " + force + ", Vitesse : " + vitesse + ", Intelligence : " + intelligence + ", Catégorie de Nen : " + nen + ", Exeperience : " + experience);
     }



}