import models.*;
import strategy.*;
import template.*;
import observer.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("--- COA SYSTEEM WORDT GESTART ---\n");

        Land syrie = new Land("Syrië", false);

        Gemeente denHaag = new Gemeente("Den Haag", 550000, 100);
        // Creates a new Gemeente object for Amsterdam with its population and capacity
        Gemeente amsterdam = new Gemeente("Amsterdam", 900000, 50);

        // Initializes a new list to hold the municipality objects
        List<Gemeente> gemeentes = new ArrayList<>();

        gemeentes.add(denHaag);
        gemeentes.add(amsterdam);

        // Creates a new AZC object located in Den Haag and links it to the Den Haag municipality
        AZC azcDenHaag = new AZC("AZC Den Haag Centrum", "Kerkstraat", "1", "2514AB", denHaag);
        AZC azcAmsterdam = new AZC("AZC Amsterdam West", "Sloterdijk", "12", "1043NX", amsterdam);
        
        Vluchteling vluchteling = new Vluchteling("Ahmed Al-Fahad", syrie);

        // Creates a new COAMedewerker object named Selin
        COAMedewerker coaMedewerker = new COAMedewerker("Selin");
        AZCMedewerker azcMedewerker = new AZCMedewerker("Johan");
        Beheerder beheerder = new Beheerder("Willem");

        // Prints a header indicating the start of the Strategy Pattern test
        System.out.println("--- 1. STRATEGY PATTERN (Automatische Plaatsing) ---");
        
        // Executes the placement strategy using the COA employee and stores the selected municipality
        Gemeente gekozen1 = coaMedewerker.voerAutomatischePlaatsing(gemeentes, new HoogsteVrijePlaatsenStrategie());
        
        // Prints the name of the chosen municipality, or 'Geen' if none was selected
        System.out.println("Gekozen Gemeente -> " + (gekozen1 != null ? gekozen1.getNaam() : "Geen") + "\n");

        
        System.out.println("--- 2. TEMPLATE METHOD PATTERN (Standaard Actiestappen) ---");
        // Creates a new registration action for the refugee
        ActieVerwerker registratieActie = new VluchtelingRegistratieActie(vluchteling);
        // Executes the standardized steps of the registration action
        registratieActie.verwerkActie();
        System.out.println();

        // Creates a new placement action to move the refugee to the Den Haag AZC
        ActieVerwerker plaatsingsActie = new VluchtelingPlaatsingActie(vluchteling, azcDenHaag);
        // Executes the standardized steps of the placement action
        plaatsingsActie.verwerkActie();
        System.out.println();

        
        System.out.println("--- 3. OBSERVER PATTERN & MEDEWERKER VERWERKING ---");
        // Creates a new inbox object to receive notifications
        Berichtenbox azcBerichtenbox = new Berichtenbox();
        // Subscribes the inbox to the refugee's dossier so it listens for updates
        vluchteling.getDossier().voegObserverToe(azcBerichtenbox);

        
        System.out.println("Systeem: Dossier van de vluchteling wordt bijgewerkt...");
        // Updates the status of the dossier to accepted, triggering the observer notification
        vluchteling.getDossier().setUitspraak("geaccepteerd");
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