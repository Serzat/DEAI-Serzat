package template;
import models.Vluchteling;
import models.AZC;


public class VluchtelingPlaatsingActie extends ActieVerwerker {

    // Private variable to store the refugee (Vluchteling) object to be placed
    private Vluchteling vluchteling;
    // Private variable to store the destination asylum center (AZC) object
    private AZC nieuwAZC;

    // Constructor method to initialize a new placement action with a specific refugee and destination AZC
    public VluchtelingPlaatsingActie(Vluchteling vluchteling, AZC nieuwAZC) {
        // Assigns the passed refugee parameter to the class variable
        this.vluchteling = vluchteling;
        // Assigns the passed AZC parameter to the class variable
        this.nieuwAZC = nieuwAZC;
    }

    // Overrides the abstract method from the parent class to check conditions before proceeding
    @Override
    protected boolean controleerVoorwaarden() {
        // Validates whether both the refugee and the new AZC objects are valid (not null)
        return vluchteling != null && nieuwAZC != null;
    }

    // Overrides the abstract method to execute the core placement action
    @Override
    protected void voerHoofdActieUit() {
        // Updates the refugee's internal state to reflect their new AZC location
        vluchteling.plaatsInAZC(nieuwAZC);
        // Adds the refugee object to the internal list of housed refugees within the new AZC
        nieuwAZC.voegVluchtelingToe(vluchteling);
        System.out.println("Hoofdactie: Vluchteling " + vluchteling.getNaam() + " is geplaatst in AZC " + nieuwAZC.getGemeente().getNaam());
    }

    // Overrides the abstract method to handle post-action administrative updates
    @Override
    protected void werkAdministratieBij() {
        // Increments the count of placed refugees in the municipality to which the AZC belongs
        nieuwAZC.getGemeente().voegVluchtelingToe();
        System.out.println("Administratie: Aantal geplaatste vluchtelingen in de gemeente is bijgewerkt.");
    }

    // Overrides the abstract method to provide end-of-process feedback
    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: Plaatsing succesvol afgerond.\n");
    }
}