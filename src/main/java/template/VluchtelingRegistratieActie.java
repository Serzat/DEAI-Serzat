package template;

import models.Vluchteling;

/** Concrete Template Method-actie voor de registratie van een vluchteling. */
public final class VluchtelingRegistratieActie extends ActieVerwerker {
    private final Vluchteling vluchteling;

    public VluchtelingRegistratieActie(Vluchteling vluchteling) {
        this.vluchteling = vluchteling;
    }

    @Override
    protected boolean controleerVoorwaarden() {
        // De constructor accepteert null zodat de Template Method de actie gecontroleerd kan annuleren.
        return vluchteling != null;
    }

    @Override
    protected void voerHoofdActieUit() {
        System.out.println("Hoofdactie: vluchteling toegevoegd aan het systeem -> "
                + vluchteling.getNaam());
    }

    @Override
    protected void werkAdministratieBij() {
        System.out.println("Administratie: dossier is actief met uitspraak '"
                + vluchteling.getDossier().getUitspraak() + "'.");
    }

    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: registratie succesvol afgerond.");
    }
}
