//Mültecinin temel bilgilerini, ülkesini, kaldığı AZCyi ve dosyasını bağladığımız ana sınıf.
package models;
// Declares the Vluchteling class, the central object of our COA application.
public class Vluchteling {
    // Private text variable to store the refugee's full name.
    private String naam;
    private Land landVanHerkomst;
    private AZC huidigAZC; 
    // Private reference to a 'Dossier' object. Every refugee has a legal file attached to them.
    private Dossier dossier;

    public Vluchteling(String naam, Land landVanHerkomst) {
        this.naam = naam;
        this.landVanHerkomst = landVanHerkomst;
        // IMPORTANT: Automatically creates a brand new 'Dossier' as soon as the refugee is registered.
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

//Sistemin ana karakteri ve merkez noktasıdır. Bütün diğer dosyalar bu karakterin etrafında birleşir. 
// Bir mülteci; bir Land'dan gelir, bir AZC'de kalır ve bir Dossier'ye sahiptir. Diğer tüm sınıfları birbirine bağlayan köprü budur.