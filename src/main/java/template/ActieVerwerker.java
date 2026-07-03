package template;

/**
 * Basisklasse van het Template Method Pattern. De vaste procesvolgorde staat
 * in de finale methode verwerkActie en kan daarom niet door subklassen worden gewijzigd.
 */
public abstract class ActieVerwerker {
    public final void verwerkActie() {
        if (!controleerVoorwaarden()) {
            System.out.println("Actie geannuleerd: voorwaarden zijn niet voldaan.");
            return;
        }

        // Deze volgorde is voor iedere concrete actie hetzelfde.
        voerHoofdActieUit();
        werkAdministratieBij();
        geefTerugkoppeling();
    }

    // Primitive operations: subklassen vullen alleen deze afzonderlijke stappen in.
    protected abstract boolean controleerVoorwaarden();

    protected abstract void voerHoofdActieUit();

    protected abstract void werkAdministratieBij();

    protected abstract void geefTerugkoppeling();
}
