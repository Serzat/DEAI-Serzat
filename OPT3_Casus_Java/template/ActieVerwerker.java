package template;


public abstract class ActieVerwerker {
    
    // Declares the public final method to lock the algorithm steps so subclasses cannot override the execution order
    public final void verwerkActie() {
        // Checks if the prerequisite conditions are met before executing the main action
        if (controleerVoorwaarden()) {
            // Executes the specific main action defined by the subclass
            voerHoofdActieUit();
            // Updates the administrative records or system statuses after the main action
            werkAdministratieBij();
            // Provides feedback or logging to confirm the completion of the action
            geefTerugkoppeling();
        // Executes the alternative block if the prerequisite conditions are not met
        } else {
            // Prints an error message to the console indicating the action was canceled due to unmet conditions
            System.out.println("Actie geannuleerd: Voorwaarden zijn niet voldaan.");
        }
    }


    // Abstract method for subclasses to define their specific validation logic, returning a boolean
    protected abstract boolean controleerVoorwaarden();
    
    // Abstract method for subclasses to define the core behavior of their specific action
    protected abstract void voerHoofdActieUit();
    
    // Abstract method for subclasses to define how they update the system administration
    protected abstract void werkAdministratieBij();
    
    // Abstract method for subclasses to define how they handle feedback or logging
    protected abstract void geefTerugkoppeling();
}