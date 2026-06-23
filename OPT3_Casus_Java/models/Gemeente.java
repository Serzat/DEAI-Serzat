package models;
import java.util.ArrayList;
import java.util.List;


public class Gemeente {
    private String naam;
    private int aantalInwoners;
    private int aangebodenPlaatsen;
    private int aantalGeplaatsteVluchtelingen; 

    private List<AZC> azcs;

    // Constructor:
    public Gemeente(String naam, int aantalInwoners, int aangebodenPlaatsen) {
        // Assigns the passed naam parameter to the class variable
        this.naam = naam;
        // Assigns the passed aantalInwoners parameter to the class variable
        this.aantalInwoners = aantalInwoners;
        // Assigns the passed aangebodenPlaatsen parameter to the class variable
        this.aangebodenPlaatsen = aangebodenPlaatsen;
        // Initializes the number of placed refugees to zero by default
        this.aantalGeplaatsteVluchtelingen = 0;
        // Initializes the azcs list as a new empty ArrayList
        this.azcs = new ArrayList<>();
    }

    // Method to add an AZC object to the list of AZCs in this municipality
    public void voegAZCToe(AZC azc) {
        // Adds the provided AZC object to the azcs list
        this.azcs.add(azc);
    }

    // Getter method to return the list of AZCs within this municipality
    public List<AZC> getAzcs() {
        // Returns the azcs list containing all associated AZC objects
        return azcs;
    }

    // Method to calculate and return the number of available free places
    public int getVrijePlaatsen() {
        // Subtracts the placed refugees from the total offered places and returns the result
        return aangebodenPlaatsen - aantalGeplaatsteVluchtelingen;
    }

    // Getter method to return the number of currently placed refugees
    public int getAantalGeplaatsteVluchtelingen() {
        // Returns the current value of the aantalGeplaatsteVluchtelingen variable
        return aantalGeplaatsteVluchtelingen;
    }

    // Method to calculate and return the relative burden of the municipality
    public double getRelatieveBelasting() {
        // Checks if the number of inhabitants is zero to prevent division by zero
        if (aantalInwoners == 0) return 0;
        // Divides the placed refugees by the inhabitants as a double and returns the result
        return (double) aantalGeplaatsteVluchtelingen / aantalInwoners;
    }

    // Method to increment the count of placed refugees by one
    public void voegVluchtelingToe() {
        // Increases the aantalGeplaatsteVluchtelingen variable by exactly one
        this.aantalGeplaatsteVluchtelingen++;
    }

    // Getter method to return the name of the municipality
    public String getNaam() { return naam; }
    public int getAantalInwoners() { return aantalInwoners; }
    public int getAangebodenPlaatsen() { return aangebodenPlaatsen; }
}