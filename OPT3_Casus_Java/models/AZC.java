//AZCnin temel adres bilgilerini, bağlı olduğu belediyeyi ve içinde kalan mültecilerin listesini içeriyor.
package models;
import java.util.ArrayList;
import java.util.List;

public class AZC {
    private String naam;
    private String straat;
    private String huisnummer;
    private String postcode;
    private Gemeente gemeente;
    private List<Vluchteling> gehuisvesteVluchtelingen;

    public AZC(String naam, String straat, String huisnummer, String postcode, Gemeente gemeente) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.gehuisvesteVluchtelingen = new ArrayList<>();
    }

    public void voegVluchtelingToe(Vluchteling v) {
        gehuisvesteVluchtelingen.add(v);
    }

    public void verwijderVluchteling(Vluchteling v) {
        gehuisvesteVluchtelingen.remove(v);
    }

    public Gemeente getGemeente() { return gemeente; }
    public List<Vluchteling> getGehuisvesteVluchtelingen() { return gehuisvesteVluchtelingen; }
}