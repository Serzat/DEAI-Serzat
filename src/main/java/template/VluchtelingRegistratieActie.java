package template;

import models.Vluchteling;

/** Concrete Template Method-action for the initial registration of a refugee. */
// 'final' class prevents inheritance and modification of the registration sequence.
public final class VluchtelingRegistratieActie extends ActieVerwerker {
    
    // Immutable reference to the refugee being registered.
    private final Vluchteling vluchteling;

    // Constructor: Injects the refugee object.
    public VluchtelingRegistratieActie(Vluchteling vluchteling) {
        this.vluchteling = vluchteling;
    }

    // Implements Step 1 of the template: Registration conditions.
    @Override
    protected boolean controleerVoorwaarden() {
        // The constructor accepts 'null' so that the Template Method can safely catch it here 
        // and abort the process gracefully instead of crashing the system.
        return vluchteling != null;
    }

    // Implements Step 2 of the template: Main registration action.
    @Override
    protected void voerHoofdActieUit() {
        System.out.println("Hoofdactie: vluchteling toegevoegd aan het systeem -> "
                + vluchteling.getNaam());
    }

    // Implements Step 3 of the template: Administrative updates.
    @Override
    protected void werkAdministratieBij() {
        // Logs the creation/status of the refugee's legal dossier.
        System.out.println("Administratie: dossier is actief met uitspraak '"
                + vluchteling.getDossier().getUitspraak() + "'.");
    }

    // Implements Step 4 of the template: Final system feedback.
    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: registratie succesvol afgerond.");
    }
}