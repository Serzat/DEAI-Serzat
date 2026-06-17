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
        System.out.println("Ana işlem: Mülteci sisteme eklendi -> " + vluchteling.getNaam());
    }

    @Override
    protected void werkAdministratieBij() {
        System.out.println("Yönetim: Yeni dosya kaydı aktif hale getirildi.");
    }

    @Override
    protected void geefTerugkoppeling() {
        System.out.println("Log: Kayıt işlemi başarıyla tamamlandı.\n");
    }
}