package mypackage;

public interface CapaciteInterface 
{
    boolean tenterActivation();  // Pour tenter d'activer la capacité
    int activer(PersonnageHxH attaquant, PersonnageHxH defenseur);  // Pour infliger des dégâts
    void incrementerTours();  // Pour suivre les tours
    boolean estDisponible();  // Vérifie si la capacité peut être activée
}