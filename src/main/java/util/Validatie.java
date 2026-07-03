package util;

import java.util.Objects;

/** Gedeelde validatiefuncties voor verplichte tekstvelden. */
public final class Validatie {
    private Validatie() {
        // Utilityklasse: bevat uitsluitend statische functies.
    }

    public static String vereisTekst(String waarde, String veldnaam) {
        Objects.requireNonNull(waarde, veldnaam + " mag niet null zijn.");
        String opgeschoond = waarde.trim();
        if (opgeschoond.isEmpty()) {
            throw new IllegalArgumentException(veldnaam + " mag niet leeg zijn.");
        }
        return opgeschoond;
    }
}
