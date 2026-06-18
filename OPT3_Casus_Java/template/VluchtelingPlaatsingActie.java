//Mültecinin bir AZC'ye yerleştirilmesi işlemini şablona uyguluyoruz.
package template;

import models.Vluchteling;
import models.AZC;

public class VluchtelingPlaatsingActie extends ActieVerwerker {
    private Vluchteling vluchteling;
    private AZC nieuwAZC;

    public VluchtelingPlaatsingActie(Vluchteling vluchteling, AZC nieuwAZC) {
        this.vluchteling = vluchteling;
        this.nieuwAZC = nieuwAZC;
    }

    @Override
    protected boolean controleerVoorwaarden() {
        // AZC ve Mülteci geçerli mi kontrolü
        return vluchteling != null && nieuwAZC != null;
    }

    @Override
    protected void voerHoofdActieUit() {
        vluchteling.plaatsInAZC(nieuwAZC);
        nieuwAZC.voegVluchtelingToe(vluchteling);
        System.out.println("Hoofdactie: Vluchteling " + vluchteling.getNaam() + " is geplaatst in AZC " + nieuwAZC.getGemeente().getNaam());
    }

    @Override
    protected void werkAdministratieBij() {
        nieuwAZC.getGemeente().voegVluchtelingToe();
        System.out.println("Administratie: Aantal geplaatste vluchtelingen in de gemeente is bijgewerkt.");
    }

    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: Plaatsing succesvol afgerond.\n");
    }
}