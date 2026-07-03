package models;

import java.util.Objects;

import static util.Validatie.vereisTekst;

/**
 * Centrale domeinklasse die een vluchteling koppelt aan het land van herkomst,
 * een persoonlijk dossier en eventueel een huidig AZC.
 */
public final class Vluchteling {
    private final String naam;
    private final Land landVanHerkomst;
    private final Dossier dossier;
    private AZC huidigAZC;

    public Vluchteling(String naam, Land landVanHerkomst) {
        this.naam = vereisTekst(naam, "naam");
        this.landVanHerkomst = Objects.requireNonNull(
                landVanHerkomst, "landVanHerkomst mag niet null zijn.");

        // Ieder nieuw geregistreerd persoon krijgt direct precies één eigen dossier.
        this.dossier = new Dossier(this);
    }

    /**
     * Plaatst of verhuist de vluchteling en houdt alle kanten van de relatie bij:
     * de vluchteling, het AZC en de gemeentelijke capaciteit.
     */
    public void plaatsInAZC(AZC nieuwAZC) {
        Objects.requireNonNull(nieuwAZC, "nieuwAZC mag niet null zijn.");
        if (huidigAZC == nieuwAZC) {
            // Dezelfde plaatsing opnieuw uitvoeren mag geen dubbele registratie opleveren.
            return;
        }

        AZC vorigAZC = huidigAZC;
        boolean verhuizingBinnenGemeente = vorigAZC != null
                && vorigAZC.getGemeente() == nieuwAZC.getGemeente();

        // Bij een verhuizing binnen dezelfde gemeente blijft het totale aantal gelijk.
        // Voor een nieuwe gemeente moet vooraf daadwerkelijk capaciteit beschikbaar zijn.
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
            // Herstel de oude toestand wanneer de nieuwe plaatsing onverwacht mislukt.
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
