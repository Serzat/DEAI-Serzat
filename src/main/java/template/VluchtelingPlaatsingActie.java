package template;

import models.AZC;
import models.Vluchteling;

/** Concrete Template Method-actie voor een nieuwe plaatsing of verhuizing. */
public final class VluchtelingPlaatsingActie extends ActieVerwerker {
    private final Vluchteling vluchteling;
    private final AZC nieuwAZC;

    public VluchtelingPlaatsingActie(Vluchteling vluchteling, AZC nieuwAZC) {
        this.vluchteling = vluchteling;
        this.nieuwAZC = nieuwAZC;
    }

    @Override
    protected boolean controleerVoorwaarden() {
        if (vluchteling == null || nieuwAZC == null || vluchteling.getHuidigAZC() == nieuwAZC) {
            return false;
        }

        AZC huidigAZC = vluchteling.getHuidigAZC();
        boolean verhuizingBinnenGemeente = huidigAZC != null
                && huidigAZC.getGemeente() == nieuwAZC.getGemeente();

        // Binnen dezelfde gemeente komt eerst een bestaande plaats vrij.
        return verhuizingBinnenGemeente || nieuwAZC.getGemeente().getVrijePlaatsen() > 0;
    }

    @Override
    protected void voerHoofdActieUit() {
        // Vluchteling.plaatsInAZC is het enige publieke mutatiepunt voor de volledige relatie.
        vluchteling.plaatsInAZC(nieuwAZC);
        System.out.println("Hoofdactie: " + vluchteling.getNaam() + " is geplaatst in "
                + nieuwAZC.getNaam() + " (" + nieuwAZC.getGemeente().getNaam() + ").");
    }

    @Override
    protected void werkAdministratieBij() {
        System.out.println("Administratie: gemeente telt nu "
                + nieuwAZC.getGemeente().getAantalGeplaatsteVluchtelingen()
                + " geplaatste vluchteling(en).");
    }

    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: plaatsing succesvol afgerond.");
    }
}
