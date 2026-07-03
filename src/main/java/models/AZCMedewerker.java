package models;

import java.util.Objects;
import observer.Bericht;

import static util.Validatie.vereisTekst;

/** Medewerker die een binnengekomen plaatsingsbericht administratief verwerkt. */
public final class AZCMedewerker {
    private final String naam;

    public AZCMedewerker(String naam) {
        this.naam = vereisTekst(naam, "naam");
    }

    public void verwerkBericht(Bericht bericht, String kamerNummer) {
        Objects.requireNonNull(bericht, "bericht mag niet null zijn.");
        String kamer = vereisTekst(kamerNummer, "kamerNummer");

        System.out.println("Systeem: AZC-medewerker " + naam + " beoordeelt de melding...");
        bericht.markeerAlsVerwerkt(kamer);
        System.out.println("MELDING VERWERKT: " + bericht.getVluchteling().getNaam()
                + " is geregistreerd in '" + kamer + "'.");
    }
}
