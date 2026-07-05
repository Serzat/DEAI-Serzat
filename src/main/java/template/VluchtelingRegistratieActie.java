package template;
import models.Vluchteling;

// Concrete implementation of the Template Method for registering a refugee.
public final class VluchtelingRegistratieActie extends ActieVerwerker {

    // Stores the refugee that will be registered.
    // 'final' ensures this reference cannot be changed after construction.
    private final Vluchteling vluchteling;

    // Constructor that receives the refugee to register.
    public VluchtelingRegistratieActie(Vluchteling vluchteling) {

        // Store the refugee so it can be used throughout the registration process.
        this.vluchteling = vluchteling;
    }

    // Step 1 of the Template Method:
    // Validate whether the registration can continue.
    @Override
    protected boolean controleerVoorwaarden() {

        // Return true only if a valid refugee object was provided.
        // If the refugee is null, the Template Method will safely
        // stop the registration process.
        return vluchteling != null;
    }

    // Step 2 of the Template Method:
    // Perform the main registration action.
    @Override
    protected void voerHoofdActieUit() {

        // Display a message indicating that the refugee
        // has been added to the system.
        System.out.println(
                "Hoofdactie: vluchteling toegevoegd aan het systeem -> "
                + vluchteling.getNaam());
    }

    // Step 3 of the Template Method:
    // Show the administrative information after registration.
    @Override
    protected void werkAdministratieBij() {

        // Display the current status (decision) stored
        // in the refugee's legal dossier.
        System.out.println(
                "Administratie: dossier is actief met uitspraak '"
                + vluchteling.getDossier().getUitspraak()
                + "'.");
    }

    // Step 4 of the Template Method:
    // Provide the final feedback after the registration is complete.
    @Override
    protected void geefTerugkoppeling() {

        // Inform the user that the registration
        // finished successfully.
        System.out.println("Log: registratie succesvol afgerond.");
    }
}