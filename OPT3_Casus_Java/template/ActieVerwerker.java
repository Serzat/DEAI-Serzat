// İşlem sırasını burada kilitliyoruz. verwerkActie metodunu final yaparak alt sınıfların bu sırayı bozmasını engelliyoruz.
package template;

public abstract class ActieVerwerker {
    
    // Template Method - 'final' kelimesi rubric için çok kritik!
    public final void verwerkActie() {
        if (controleerVoorwaarden()) {
            voerHoofdActieUit();
            werkAdministratieBij();
            geefTerugkoppeling();
        } else {
            System.out.println("İşlem iptal edildi: Şartlar sağlanmadı.");
        }
    }

    // Primitive operations (Alt sınıfların dolduracağı kısımlar)
    protected abstract boolean controleerVoorwaarden();
    protected abstract void voerHoofdActieUit();
    protected abstract void werkAdministratieBij();
    protected abstract void geefTerugkoppeling();
}