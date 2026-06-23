
package observer;


import models.Vluchteling;


public class Bericht {
    private Vluchteling vluchteling;
    private String typeBericht;
    private String inhoud;
    private boolean isVerwerkt;
    private String kamer;

    // Constructor:
    public Bericht(Vluchteling vluchteling, String typeBericht, String inhoud) {
        // Assigns the passed vluchteling parameter to the class variable
        this.vluchteling = vluchteling;
        // Assigns the passed typeBericht parameter to the class variable
        this.typeBericht = typeBericht;
        // Assigns the passed inhoud parameter to the class variable
        this.inhoud = inhoud;
        // Initializes the isVerwerkt status to false by default
        this.isVerwerkt = false; 
    }

    // Getter method to return the content of the message
    public String getInhoud() { return inhoud; }
    public Vluchteling getVluchteling() { return vluchteling; }
    public String getTypeBericht() { return typeBericht; }
    public boolean isVerwerkt() { return isVerwerkt; }
    public String getKamer() { return kamer; }

    // Method to mark the message as processed and assign a room
    public void markeerAlsVerwerkt(String kamer) { 
        // Sets the isVerwerkt status to true
        this.isVerwerkt = true; 
        // Assigns the provided room string to the kamer variable
        this.kamer = kamer;
    }
}