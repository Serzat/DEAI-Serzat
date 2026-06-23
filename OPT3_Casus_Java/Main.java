// Imports all classes from the models package
import models.*;
// Imports all classes from the strategy package
import strategy.*;
// Imports all classes from the template package
import template.*;
// Imports all classes from the observer package
import observer.*;
// Imports the ArrayList class from the java utility library
import java.util.ArrayList;
// Imports the List interface from the java utility library
import java.util.List;

// Declares the public class named Main, which serves as the entry point of the application
public class Main {
    // Defines the main method which is executed when the program starts
    public static void main(String[] args) {
        // Prints a startup message for the COA system to the console
        System.out.println("--- COA SYSTEEM WORDT GESTART ---\n");

        // Creates a new Land object representing Syria, marked as not safe (false)
        Land syrie = new Land("Syrië", false);
        // Creates a new Gemeente object for Den Haag with its population and capacity
        Gemeente denHaag = new Gemeente("Den Haag", 550000, 100);
        // Creates a new Gemeente object for Amsterdam with its population and capacity
        Gemeente amsterdam = new Gemeente("Amsterdam", 900000, 50);

        // Initializes a new list to hold the municipality objects
        List<Gemeente> gemeentes = new ArrayList<>();
        // Adds the Den Haag municipality to the list
        gemeentes.add(denHaag);
        // Adds the Amsterdam municipality to the list
        gemeentes.add(amsterdam);

        // Creates a new AZC object located in Den Haag and links it to the Den Haag municipality
        AZC azcDenHaag = new AZC("AZC Den Haag Centrum", "Kerkstraat", "1", "2514AB", denHaag);
        // Creates a new AZC object located in Amsterdam and links it to the Amsterdam municipality
        AZC azcAmsterdam = new AZC("AZC Amsterdam West", "Sloterdijk", "12", "1043NX", amsterdam);
        
        // Creates a new Vluchteling object for a refugee coming from Syria
        Vluchteling vluchteling = new Vluchteling("Ahmed Al-Fahad", syrie);

        // Creates a new COAMedewerker object named Selin
        COAMedewerker coaMedewerker = new COAMedewerker("Selin");
        // Creates a new AZCMedewerker object named Johan
        AZCMedewerker azcMedewerker = new AZCMedewerker("Johan");
        // Creates a new Beheerder object named Willem
        Beheerder beheerder = new Beheerder("Willem");

        // Prints a header indicating the start of the Strategy Pattern test
        System.out.println("--- 1. STRATEGY PATTERN (Automatische Plaatsing) ---");
        // Executes the placement strategy using the COA employee and stores the selected municipality
        Gemeente gekozen1 = coaMedewerker.voerAutomatischePlaatsing(gemeentes, new HoogsteVrijePlaatsenStrategie());
        // Prints the name of the chosen municipality, or 'Geen' if none was selected
        System.out.println("Gekozen Gemeente -> " + (gekozen1 != null ? gekozen1.getNaam() : "Geen") + "\n");

        // Prints a header indicating the start of the Template Method Pattern test
        System.out.println("--- 2. TEMPLATE METHOD PATTERN (Standaard Actiestappen) ---");
        // Creates a new registration action for the refugee
        ActieVerwerker registratieActie = new VluchtelingRegistratieActie(vluchteling);
        // Executes the standardized steps of the registration action
        registratieActie.verwerkActie();
        // Prints an empty line for visual separation in the console output
        System.out.println();

        // Creates a new placement action to move the refugee to the Den Haag AZC
        ActieVerwerker plaatsingsActie = new VluchtelingPlaatsingActie(vluchteling, azcDenHaag);
        // Executes the standardized steps of the placement action
        plaatsingsActie.verwerkActie();
        // Prints an empty line for visual separation in the console output
        System.out.println();

        // Prints a header indicating the start of the Observer Pattern test
        System.out.println("--- 3. OBSERVER PATTERN & MEDEWERKER VERWERKING ---");
        // Creates a new inbox object to receive notifications
        Berichtenbox azcBerichtenbox = new Berichtenbox();
        // Subscribes the inbox to the refugee's dossier so it listens for updates
        vluchteling.getDossier().voegObserverToe(azcBerichtenbox);

        // Prints a message indicating that the refugee's dossier is being updated
        System.out.println("Systeem: Dossier van de vluchteling wordt bijgewerkt...");
        // Updates the status of the dossier to accepted, triggering the observer notification
        vluchteling.getDossier().setUitspraak("geaccepteerd");
        // Prints an empty line for visual separation in the console output
        System.out.println();

        // Checks if there are any unread messages in the AZC inbox
        if (!azcBerichtenbox.getOngelezenBerichten().isEmpty()) {
            // Retrieves the first unread message from the inbox
            Bericht gelenBildirim = azcBerichtenbox.getOngelezenBerichten().get(0);
            // Processes the retrieved message using the AZC employee and assigns a room
            azcMedewerker.verwerkBericht(gelenBildirim, "Kamer 204 / Blok B");
        }

        // Prints a header indicating the start of the Reporting test
        System.out.println("--- 4. RAPPORTAGE (Beheerder Overzicht) ---");
        // Requests the administrator to generate a system report for all municipalities
        beheerder.vraagRapportageOp(gemeentes);
    }
}