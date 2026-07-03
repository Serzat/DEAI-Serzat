package observer;

import java.util.Objects;
import models.Vluchteling;

import static util.Validatie.vereisTekst;

/**
 * Notificatie over een vluchteling. Een bericht begint onverwerkt en kan één
 * keer door een AZC-medewerker aan een kamer worden gekoppeld.
 */
public final class Bericht {
    private final Vluchteling vluchteling;
    private final String typeBericht;
    private final String inhoud;
    private boolean verwerkt;
    private String kamer;

    public Bericht(Vluchteling vluchteling, String typeBericht, String inhoud) {
        this.vluchteling = Objects.requireNonNull(
                vluchteling, "vluchteling mag niet null zijn.");
        this.typeBericht = vereisTekst(typeBericht, "typeBericht");
        this.inhoud = vereisTekst(inhoud, "inhoud");
    }

    public String getInhoud() {
        return inhoud;
    }

    public Vluchteling getVluchteling() {
        return vluchteling;
    }

    public String getTypeBericht() {
        return typeBericht;
    }

    public boolean isVerwerkt() {
        return verwerkt;
    }

    public String getKamer() {
        return kamer;
    }

    public void markeerAlsVerwerkt(String kamer) {
        if (verwerkt) {
            // Dubbele verwerking zou de oorspronkelijke administratie overschrijven.
            throw new IllegalStateException("Dit bericht is al verwerkt.");
        }
        this.kamer = vereisTekst(kamer, "kamer");
        this.verwerkt = true;
    }
}
