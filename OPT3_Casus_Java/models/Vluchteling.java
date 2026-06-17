//Mültecinin temel bilgilerini, ülkesini, kaldığı AZCyi ve dosyasını bağladığımız ana sınıf.
package models;
public class Vluchteling {
    private String naam;
    private Land landVanHerkomst;
    private AZC huidigAZC; 
    private Dossier dossier;

    public Vluchteling(String naam, Land landVanHerkomst) {
        this.naam = naam;
        this.landVanHerkomst = landVanHerkomst;
        this.dossier = new Dossier(); // Kayıt anında otomatik dosya oluşturulur[cite: 56].
        this.huidigAZC = null; // Başlangıçta bir AZC'ye atanmamış olabilir[cite: 54].
    }

    public void plaatsInAZC(AZC nieuwAZC) {
        this.huidigAZC = nieuwAZC;
    }

    public AZC getHuidigAZC() { return huidigAZC; }
    public Dossier getDossier() { return dossier; }
    public String getNaam() { return naam; }
}