package models;
import java.util.ArrayList;
import java.util.List;

public class AZC {
    private String naam;
    private String straat;
    private String huisnummer;
    private String postcode;
    // Private variable to store the associated Gemeente (municipality) object
    private Gemeente gemeente;
    // Private list variable to store the refugees housed in this center
    private List<Vluchteling> gehuisvesteVluchtelingen;

    // Constructor:
    public AZC(String naam, String straat, String huisnummer, String postcode, Gemeente gemeente) {
        this.naam = naam;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        // Initializes the gehuisvesteVluchtelingen list as a new empty ArrayList
        this.gehuisvesteVluchtelingen = new ArrayList<>();
        
        // Checks if the provided gemeente object is not null before proceeding
        if (gemeente != null) {
            // Calls the voegAZCToe method on the gemeente object to link this AZC to it
            gemeente.voegAZCToe(this);
        }
    }

    // Method to add a refugee to the list of housed refugees in this center
    public void voegVluchtelingToe(Vluchteling v) {
        // Adds the provided vluchteling object to the gehuisvesteVluchtelingen list
        gehuisvesteVluchtelingen.add(v);
    }

    // Method to remove a refugee from the list of housed refugees in this center
    public void verwijderVluchteling(Vluchteling v) {
        // Removes the provided vluchteling object from the gehuisvesteVluchtelingen list
        gehuisvesteVluchtelingen.remove(v);
    }

    // Getter method to return the associated Gemeente object
    public Gemeente getGemeente() { return gemeente; }
    // Getter method to return the name of the AZC
    public String getNaam() { return naam; }
    // Getter method to return the street name of the AZC
    public String getStraat() { return straat; }
    // Getter method to return the house number of the AZC
    public String getHuisnummer() { return huisnummer; }
    // Getter method to return the postal code of the AZC
    public String getPostcode() { return postcode; }
    // Getter method to return the complete list of refugees housed here
    public List<Vluchteling> getGehuisvesteVluchtelingen() { return gehuisvesteVluchtelingen; }
}