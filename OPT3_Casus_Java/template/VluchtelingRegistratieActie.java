//Bir mültecinin sisteme kaydedilmesi işlemini şablona uyguluyoruz.
package template;

import models.Vluchteling;

public class VluchtelingRegistratieActie extends ActieVerwerker {
    private Vluchteling vluchteling;

    public VluchtelingRegistratieActie(Vluchteling vluchteling) {
        this.vluchteling = vluchteling;
    }

    @Override
    protected boolean controleerVoorwaarden() {
        // İsim ve ülke girilmiş mi kontrolü
        return vluchteling != null && vluchteling.getNaam() != null && !vluchteling.getNaam().isEmpty();
    }

    @Override
    protected void voerHoofdActieUit() {
        System.out.println("\"Hoofdactie: Vluchteling is toegevoegd aan het systeem -> " + vluchteling.getNaam());
    }

    @Override
    protected void werkAdministratieBij() {
        System.out.println("Administratie: Nieuw dossier is geactiveerd.");
    }

    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: Registratie succesvol afgerond.\n");
    }
}