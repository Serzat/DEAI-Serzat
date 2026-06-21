//AZCnin temel adres bilgilerini, bağlı olduğu belediyeyi ve içinde kalan mültecilerin listesini içeriyor.
package models;
import java.util.ArrayList;
import java.util.List;

public class AZC {
    private String naam;
    private String straat;
    private String huisnummer;
    private String postcode;
    // A private variable of type 'Gemeente'. Links this AZC to a specific municipality object.
    private Gemeente gemeente;
    // A private List that holds 'Vluchteling' (Refugee) objects. Keeps track of who lives here.
    private List<Vluchteling> gehuisvesteVluchtelingen;
    // Constructor method:
    public AZC(String naam, String straat, String huisnummer, String postcode, Gemeente gemeente) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        // Initializes the empty list as an ArrayList so we can add refugees to it later without errors.
        this.gehuisvesteVluchtelingen = new ArrayList<>();
    }
    // Method to add a refugee to this AZC. Takes a 'Vluchteling' object as a parameter (v).
    public void voegVluchtelingToe(Vluchteling v) {
        // Uses the .add() method of the List to put the refugee 'v' into the memory of this AZC.
        gehuisvesteVluchtelingen.add(v);
    }
    // Method to remove a refugee from this AZC (e.g., if they leave or transfer).
    public void verwijderVluchteling(Vluchteling v) {
        // Uses the .remove() method of the List to delete the refugee 'v' from this AZC's memory.
        gehuisvesteVluchtelingen.remove(v);
    }
    // Getter method: Returns the specific Gemeente object that this AZC belongs to.
    public Gemeente getGemeente() { return gemeente; }
    public String getNaam() { return naam; }
    public String getStraat() { return straat; }
    public String getHuisnummer() { return huisnummer; }
    public String getPostcode() { return postcode; }
    // Getter method: Returns the complete list of refugees currently living in this AZC.
    public List<Vluchteling> getGehuisvesteVluchtelingen() { return gehuisvesteVluchtelingen; }
}

//mutlaka bir Gemeente'ye (Belediyeye) bağlı olmak zorundadır. İçinde, o an kampta kalan mültecilerin tutulduğu bir liste barındırır.