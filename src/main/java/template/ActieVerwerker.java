package template;

public abstract class ActieVerwerker {
    public final void verwerkActie() {
        if (!controleerVoorwaarden()) {
            System.out.println("Actie geannuleerd: voorwaarden zijn niet voldaan.");
            return;
        }

        voerHoofdActieUit();
        werkAdministratieBij();
        geefTerugkoppeling();
    }

    protected abstract boolean controleerVoorwaarden();

    protected abstract void voerHoofdActieUit();

    protected abstract void werkAdministratieBij();

    protected abstract void geefTerugkoppeling();
}
