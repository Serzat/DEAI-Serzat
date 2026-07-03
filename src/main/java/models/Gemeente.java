package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static util.Validatie.vereisTekst;

public final class Gemeente {
    private final String naam;
    private final int aantalInwoners;
    private final int aangebodenPlaatsen;
    private int aantalGeplaatsteVluchtelingen;
    private final List<AZC> azcs = new ArrayList<>();

    public Gemeente(String naam, int aantalInwoners, int aangebodenPlaatsen) {
        this.naam = vereisTekst(naam, "naam");
        if (aantalInwoners <= 0) {
            throw new IllegalArgumentException("Het aantal inwoners moet groter zijn dan 0.");
        }
        if (aangebodenPlaatsen < 0) {
            throw new IllegalArgumentException("Het aantal aangeboden plaatsen mag niet negatief zijn.");
        }
        this.aantalInwoners = aantalInwoners;
        this.aangebodenPlaatsen = aangebodenPlaatsen;
    }

    public void voegAZCToe(AZC azc) {
        Objects.requireNonNull(azc, "AZC mag niet null zijn.");
        if (azc.getGemeente() != this) {
            throw new IllegalArgumentException("Het AZC behoort niet tot deze gemeente.");
        }
        if (!azcs.contains(azc)) {
            azcs.add(azc);
        }
    }

    public List<AZC> getAzcs() {
        return List.copyOf(azcs);
    }

    public int getVrijePlaatsen() {
        return aangebodenPlaatsen - aantalGeplaatsteVluchtelingen;
    }

    public int getAantalGeplaatsteVluchtelingen() {
        return aantalGeplaatsteVluchtelingen;
    }

    public double getRelatieveBelasting() {
        return (double) aantalGeplaatsteVluchtelingen / aantalInwoners;
    }

    public void voegVluchtelingToe() {
        if (getVrijePlaatsen() <= 0) {
            throw new IllegalStateException("Gemeente " + naam + " heeft geen vrije plaatsen meer.");
        }
        aantalGeplaatsteVluchtelingen++;
    }

    public void verwijderVluchteling() {
        if (aantalGeplaatsteVluchtelingen <= 0) {
            throw new IllegalStateException("Er kan geen plaatsing uit gemeente " + naam + " worden verwijderd.");
        }
        aantalGeplaatsteVluchtelingen--;
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalInwoners() {
        return aantalInwoners;
    }

    public int getAangebodenPlaatsen() {
        return aangebodenPlaatsen;
    }

}
