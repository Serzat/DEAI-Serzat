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
        this.dossier = new Dossier(this); // GÜNCELLEME: Dosyaya mültecinin kendisini bağladık.
        this.huidigAZC = null;
    }

    public void plaatsInAZC(AZC nieuwAZC) {
        this.huidigAZC = nieuwAZC;
    }

    public AZC getHuidigAZC() { return huidigAZC; }
    public Dossier getDossier() { return dossier; }
    public String getNaam() { return naam; }
}