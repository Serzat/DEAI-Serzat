package template;
import models.Vluchteling;

// Declares the public class VluchtelingRegistratieActie which extends the abstract ActieVerwerker class
public class VluchtelingRegistratieActie extends ActieVerwerker {
    // Private variable to store the refugee (Vluchteling) object associated with this action
    private Vluchteling vluchteling;

    // Constructor method to initialize a new registration action with a specific refugee
    public VluchtelingRegistratieActie(Vluchteling vluchteling) {
        // Assigns the passed refugee parameter to the class variable
        this.vluchteling = vluchteling;
    }

    // Overrides the abstract method from the parent class to check the required conditions before proceeding
    @Override
    protected boolean controleerVoorwaarden() {
        // Returns true only if the refugee object exists, has a name, and the name is not an empty string
        return vluchteling != null && vluchteling.getNaam() != null && !vluchteling.getNaam().isEmpty();
    }

    // Overrides the abstract method to execute the main action of the registration process
    @Override
    protected void voerHoofdActieUit() {
        System.out.println("Hoofdactie: Vluchteling is toegevoegd aan het systeem -> " + vluchteling.getNaam());
    }

    // Overrides the abstract method to update the administration records after the main action
    @Override
    protected void werkAdministratieBij() {
        System.out.println("Administratie: Nieuw dossier is geactiveerd.");
    }

    // Overrides the abstract method to provide feedback or logging at the end of the process
    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: Registratie succesvol afgerond.\n");
    }
}