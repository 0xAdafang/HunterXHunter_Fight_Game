package mypackage;

public abstract class CapaciteNen {
    protected String nom;
    protected int cooldown;
    protected int tourRestants;

    public CapaciteNen(String nom, int cooldown) {
        this.nom = nom;
        this.cooldown = cooldown;
        this.tourRestants = 0;
    }

    public boolean estDisponible() {
        return tourRestants == 0;
    }

    public void resetCooldown() {
        tourRestants = cooldown;
    }

    public abstract int activer(PersonnageHxH attaquant, PersonnageHxH defenseur, boolean coupCritique);

    public String getNom() {
        return nom;
    }

    public void decrementerCooldown() {
        if (tourRestants > 0) { // Correction ici pour utiliser tourRestants
            tourRestants--;
        }
    }

    public void setCooldown(int tours) { // Modification ici pour accepter un paramètre
        this.cooldown = tours;
    }
}

