package template;

// Base class for all actions that follow the same processing steps.
public abstract class ActieVerwerker {

    // The Template Method that defines the fixed execution order.
    // 'final' prevents subclasses from changing this sequence.
    public final void verwerkActie() {

        // First check if the action is allowed to continue.
        if (!controleerVoorwaarden()) {

            // Show a message when the required conditions are not met.
            System.out.println("Actie geannuleerd: voorwaarden zijn niet voldaan.");

            // Stop the method immediately.
            return;
        }

        // Execute the main action.
        voerHoofdActieUit();

        // Update data or administration after the action.
        werkAdministratieBij();

        // Display the result or feedback to the user.
        geefTerugkoppeling();
    }

    // Checks whether the action is allowed to start.
    // Each subclass provides its own validation.
    protected abstract boolean controleerVoorwaarden();

    // Performs the main task of the action.
    // Each subclass implements its own logic.
    protected abstract void voerHoofdActieUit();

    // Updates the administration or stored data.
    // Each subclass decides what needs to be updated.
    protected abstract void werkAdministratieBij();

    // Gives feedback after the action is completed.
    // Each subclass defines its own message or output.
    protected abstract void geefTerugkoppeling();
}