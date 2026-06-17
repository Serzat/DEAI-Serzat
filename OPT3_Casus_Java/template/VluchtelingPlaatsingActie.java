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
        System.out.println("Ana işlem: " + vluchteling.getNaam() + " adlı mülteci " + nieuwAZC.getGemeente().getNaam() + " AZC'sine yerleştirildi.");
    }

    @Override
    protected void werkAdministratieBij() {
        nieuwAZC.getGemeente().voegVluchtelingToe();
        System.out.println("Yönetim: Belediyenin yerleştirilmiş mülteci sayısı güncellendi.");
    }

    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: Yerleştirme (Plaatsing) işlemi tamamlandı.\n");
    }
}