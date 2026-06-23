
package models;
public class Vluchteling {
    private String naam;
    private Land landVanHerkomst;
    private AZC huidigAZC; 
    // Private reference to a 'Dossier' object. Every refugee has a legal file attached to them.
    private Dossier dossier;

    public Vluchteling(String naam, Land landVanHerkomst) {
        this.naam = naam;
        this.landVanHerkomst = landVanHerkomst;
        // Automatically creates a brand new 'Dossier' as soon as the refugee is registered.
        // 'this' is passed so the Dossier knows EXACTLY which refugee it belongs to.
        this.dossier = new Dossier(this); 
        // When first registered, the refugee is not in an AZC yet, so it is set to 'null' (empty).
        this.huidigAZC = null;
    }
    // Method to assign or move the refugee to a specific AZC. 
    // It updates the 'huidigAZC' variable with the new location.
    public void plaatsInAZC(AZC nieuwAZC) {
        this.huidigAZC = nieuwAZC;
    }
// Getter method: Returns the AZC object where the refugee is currently housed.
    public AZC getHuidigAZC() { return huidigAZC; }
    // Getter method: Returns the specific legal dossier belonging to this refugee.
    public Dossier getDossier() { return dossier; }
    // Getter method: Returns the refugee's name.
    public String getNaam() { return naam; }
    // Getter method: Returns the 'Land' object representing their country of origin.
    public Land getLandVanHerkomst() { return landVanHerkomst; }
}
