// Defines the package name for the data models
package models;

// Imports the RapportageService class from the service package
import service.RapportageService;
// Imports the List interface from the java utility library
import java.util.List;

// Declares the public class named Beheerder
public class Beheerder {
    // Private string variable to store the name of the administrator
    private String naam;
    // Private variable to store the reporting service instance
    private RapportageService rapportageService;

    // Constructor to initialize a new Beheerder object with a specific name
    public Beheerder(String naam) {
        // Assigns the passed naam parameter to the class variable
        this.naam = naam;
        // Initializes the rapportageService by creating a new instance of it
        this.rapportageService = new RapportageService();
    }

    // Method to request and generate a report for a provided list of municipalities
    public void vraagRapportageOp(List<Gemeente> gemeentes) {
        // Prints a system message indicating that the administrator has requested a report
        System.out.println("Systeem: Beheerder " + naam + " heeft een systeemrapport aangevraagd.");
        // Calls the genereerManagementRapport method on the reporting service instance
        rapportageService.genereerManagementRapport(gemeentes);
    }
}