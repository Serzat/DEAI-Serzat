// Defines the package name for the service classes
package service;

// Imports the Gemeente model class from the models package
import models.Gemeente;
// Imports the AZC model class from the models package
import models.AZC;
// Imports the List interface from the java utility library
import java.util.List;

// Declares the public class named RapportageService
public class RapportageService {
    // Method to generate and print a management report for a list of municipalities
    public void genereerManagementRapport(List<Gemeente> gemeentes) {
        // Prints a decorative top border for the report header to the console
        System.out.println("\n==================================================");
        // Prints the title of the management report to the console
        System.out.println("         COA SYSTEEM MANAGEMENT RAPPORT           ");
        // Prints a decorative bottom border for the report header to the console
        System.out.println("==================================================");
        // Starts a for-each loop to iterate through every Gemeente object in the provided list
        for (Gemeente g : gemeentes) {
            // Prints the name of the current municipality
            System.out.println("Naam Gemeente: " + g.getNaam());
            // Prints the total number of inhabitants of the current municipality
            System.out.println("Totaal aantal inwoners: " + g.getAantalInwoners());
            // Prints the total number of offered places in the current municipality
            System.out.println("Totaal aangeboden plaatsen: " + g.getAangebodenPlaatsen());
            // Prints the actual number of placed refugees in the current municipality
            System.out.println("Aantal geplaatste vluchtelingen: " + g.getAantalGeplaatsteVluchtelingen());
            // Prints the remaining free places in the current municipality
            System.out.println("Resterende vrije plaatsen: " + g.getVrijePlaatsen());
            // Prints a header indicating the start of the associated AZCs list
            System.out.println("AZC's binnen deze gemeente:");
            // Checks if the list of AZCs for the current municipality is empty
            if (g.getAzcs().isEmpty()) {
                // Prints a message stating that there are no active AZCs in this municipality
                System.out.println("  - Er is geen actief AZC in deze gemeente.");
            } else {
                // Starts a for-each loop to iterate through every AZC object associated with this municipality
                for (AZC azc : g.getAzcs()) {
                    // Prints the name and full address of the current AZC object
                    System.out.println("  -> " + azc.getNaam() + " (Adres: " + azc.getStraat() + " " + azc.getHuisnummer() + ")");
                }
            }
            // Prints a separator line to distinguish between different municipalities
            System.out.println("--------------------------------------------------");
        }
    }
}