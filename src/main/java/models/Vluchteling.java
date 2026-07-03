package models;

import java.util.Objects;

import static util.Validatie.vereisTekst;

public final class Vluchteling {
    private final String naam;
    private final Land landVanHerkomst;
    private final Dossier dossier;
    private AZC huidigAZC;

    public Vluchteling(String naam, Land landVanHerkomst) {
        this.naam = vereisTekst(naam, "naam");
        this.landVanHerkomst = Objects.requireNonNull(
                landVanHerkomst, "landVanHerkomst mag niet null zijn.");
        this.dossier = new Dossier(this);
    }

    public void plaatsInAZC(AZC nieuwAZC) {
        Objects.requireNonNull(nieuwAZC, "nieuwAZC mag niet null zijn.");
        if (huidigAZC == nieuwAZC) {
            return;
        }

        AZC vorigAZC = huidigAZC;
        boolean verhuizingBinnenGemeente = vorigAZC != null
                && vorigAZC.getGemeente() == nieuwAZC.getGemeente();

        if (!verhuizingBinnenGemeente && nieuwAZC.getGemeente().getVrijePlaatsen() <= 0) {
            throw new IllegalStateException(
                    "Er zijn geen vrije plaatsen in gemeente " + nieuwAZC.getGemeente().getNaam() + ".");
        }

        if (vorigAZC != null) {
            vorigAZC.verwijderVluchteling(this);
        }

        try {
            nieuwAZC.voegVluchtelingToe(this);
            huidigAZC = nieuwAZC;
        } catch (RuntimeException fout) {
            if (vorigAZC != null) {
                vorigAZC.voegVluchtelingToe(this);
                huidigAZC = vorigAZC;
            }
            throw fout;
        }
    }

    public AZC getHuidigAZC() {
        return huidigAZC;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public String getNaam() {
        return naam;
    }

    public Land getLandVanHerkomst() {
        return landVanHerkomst;
    }

}
