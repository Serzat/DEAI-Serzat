// Defines the package name for the data models
package models;

// Imports the PlaatsingsContext class from the strategy package
import strategy.PlaatsingsContext;
// Imports the PlaatsingsStrategie interface from the strategy package
import strategy.PlaatsingsStrategie;
// Imports the List interface from the java utility library
import java.util.List;

// Declares the public class named COAMedewerker
public class COAMedewerker {
    // Private string variable to store the name of the COA employee
    private String naam;
    // Private variable to store the placement context instance for strategy execution
    private PlaatsingsContext context;

    // Constructor to initialize a new COAMedewerker object with a specific name
    public COAMedewerker(String naam) {
        // Assigns the passed naam parameter to the class variable
        this.naam = naam;
        // Initializes the context by creating a new instance of PlaatsingsContext
        this.context = new PlaatsingsContext();
    }

    // Method to execute an automatic placement process using a provided strategy
    public Gemeente voerAutomatischePlaatsing(List<Gemeente> gemeentes, PlaatsingsStrategie strategie) {
        // Prints a system message indicating that the COA employee is starting the placement process
        System.out.println("Systeem: COA Medewerker " + naam + " voert het automatische plaatsingsproces uit.");
        // Sets the provided strategy into the placement context instance
        context.setStrategie(strategie);
        // Executes the placement process and returns the selected municipality
        return context.voerPlaatsingUit(gemeentes);
    }
}