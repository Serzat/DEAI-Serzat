package models;
import observer.Bericht;

// Declares the public class named AZCMedewerker
public class AZCMedewerker {
    // Private string variable to store the name of the AZC employee
    private String naam;

    // Constructor to initialize a new AZCMedewerker object with a specific name
    public AZCMedewerker(String naam) {
        // Assigns the passed naam parameter to the class variable
        this.naam = naam;
    }

    // Method to process an incoming message and assign a room number
    public void verwerkBericht(Bericht bericht, String kamerNummer) {
        // Prints a system message indicating that the AZC employee is reviewing the message
        System.out.println("Systeem: AZC Medewerker " + naam + " beoordeelt de inkomende melding...");
        
        // Calls the markeerAlsVerwerkt method on the message object with the provided room number
        bericht.markeerAlsVerwerkt(kamerNummer);
        
        // Prints a success message showing the refugee's name and their newly assigned room
        System.out.println("MELDING VERWERKT: Vluchteling " + bericht.getVluchteling().getNaam() + 
                           " is succesvol geregistreerd in kamer/afdeling '" + kamerNummer + "'.");
        // Prints the current processing status of the message to confirm it is handled
        System.out.println("Status van de operatie: Verwerkt = " + bericht.isVerwerkt() + "\n");
    }
}