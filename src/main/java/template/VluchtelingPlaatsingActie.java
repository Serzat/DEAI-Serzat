package template;
import models.AZC;
import models.Vluchteling;

// Concrete implementation of the Template Method for placing or relocating a refugee.
public final class VluchtelingPlaatsingActie extends ActieVerwerker {

    // Stores the refugee involved in this placement action.
    // 'final' means the reference cannot be changed after construction.
    private final Vluchteling vluchteling;

    // Stores the destination AZC where the refugee will be placed.
    // 'final' ensures this reference remains constant.
    private final AZC nieuwAZC;

    // Constructor that receives all required data for this action.
    public VluchtelingPlaatsingActie(Vluchteling vluchteling, AZC nieuwAZC) {

        // Save the refugee so it can be used throughout the action.
        this.vluchteling = vluchteling;

        // Save the destination AZC.
        this.nieuwAZC = nieuwAZC;
    }

    // Step 1 of the Template Method:
    // Validates whether the placement is allowed before continuing.
    @Override
    protected boolean controleerVoorwaarden() {

        // Stop immediately if:
        // - the refugee does not exist,
        // - the destination AZC does not exist,
        // - or the refugee is already assigned to this AZC.
        if (vluchteling == null || nieuwAZC == null || vluchteling.getHuidigAZC() == nieuwAZC) {
            return false;
        }

        // Retrieve the refugee's current AZC.
        // This is needed to determine whether this is a relocation.
        AZC huidigAZC = vluchteling.getHuidigAZC();

        // Check whether this is a relocation within the same municipality.
        // First, verify that the refugee already has a current AZC.
        // If so, compare the municipality of the current AZC with the municipality
        // of the destination AZC. If both AZCs belong to the same municipality,
        // this variable becomes true, meaning the refugee is only changing AZCs
        // without leaving the municipality.
        boolean verhuizingBinnenGemeente =
                huidigAZC != null
                && huidigAZC.getGemeente() == nieuwAZC.getGemeente();

        // Allow the placement if:
        // - the move stays within the same municipality, or
        // - the destination municipality still has free capacity.
        return verhuizingBinnenGemeente
                || nieuwAZC.getGemeente().getVrijePlaatsen() > 0;
    }

    // Step 2 of the Template Method:
    // Executes the actual placement.
    @Override
    protected void voerHoofdActieUit() {

        // Place the refugee in the new AZC.
        // This method also updates the relationship between
        // the refugee and the AZC.
        vluchteling.plaatsInAZC(nieuwAZC);

        // Print a confirmation showing where the refugee was placed.
        System.out.println(
                "Hoofdactie: "
                + vluchteling.getNaam()
                + " is geplaatst in "
                + nieuwAZC.getNaam()
                + " ("
                + nieuwAZC.getGemeente().getNaam()
                + ").");
    }

    // Step 3 of the Template Method:
    // Shows the updated administrative information.
    @Override
    protected void werkAdministratieBij() {

        // Print the current number of refugees registered
        // in the destination municipality.
        System.out.println(
                "Administratie: gemeente telt nu "
                + nieuwAZC.getGemeente().getAantalGeplaatsteVluchtelingen()
                + " geplaatste vluchteling(en).");
    }

    // Step 4 of the Template Method:
    // Gives the final feedback after the complete process.
    @Override
    protected void geefTerugkoppeling() {

        // Inform the user that the placement finished successfully.
        System.out.println("Log: plaatsing succesvol afgerond.");
    }
}