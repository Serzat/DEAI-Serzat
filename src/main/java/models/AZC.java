package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static util.Validatie.vereisTekst;

/**
 * Een asielzoekerscentrum met adresgegevens, een gekoppelde gemeente en
 * de vluchtelingen die op dit moment in het centrum zijn gehuisvest.
 */
public final class AZC {
    private final String naam;
    private final String straat;
    private final String huisnummer;
    private final String postcode;
    private final Gemeente gemeente;
    private final List<Vluchteling> gehuisvesteVluchtelingen = new ArrayList<>();

    public AZC(String naam, String straat, String huisnummer, String postcode, Gemeente gemeente) {
        this.naam = vereisTekst(naam, "naam");
        this.straat = vereisTekst(straat, "straat");
        this.huisnummer = vereisTekst(huisnummer, "huisnummer");
        this.postcode = vereisTekst(postcode, "postcode");
        this.gemeente = Objects.requireNonNull(gemeente, "gemeente mag niet null zijn.");

        // Houd de relatie Gemeente -> AZC direct bij de constructie consistent.
        gemeente.voegAZCToe(this);
    }

    /**
     * Interne mutatiemethode. Alleen modelklassen in hetzelfde package mogen
     * bewoners toevoegen; externe code moet altijd Vluchteling.plaatsInAZC gebruiken.
     */
    void voegVluchtelingToe(Vluchteling vluchteling) {
        Objects.requireNonNull(vluchteling, "vluchteling mag niet null zijn.");
        if (gehuisvesteVluchtelingen.contains(vluchteling)) {
            return;
        }

        // Eerst capaciteit reserveren, daarna de bewoner toevoegen.
        gemeente.voegVluchtelingToe();
        gehuisvesteVluchtelingen.add(vluchteling);
    }

    /**
     * Interne mutatiemethode voor een verhuizing. De gemeenteteller wordt alleen
     * verlaagd wanneer de vluchteling daadwerkelijk in dit AZC stond geregistreerd.
     */
    void verwijderVluchteling(Vluchteling vluchteling) {
        Objects.requireNonNull(vluchteling, "vluchteling mag niet null zijn.");
        if (gehuisvesteVluchtelingen.remove(vluchteling)) {
            gemeente.verwijderVluchteling();
        }
    }

    public Gemeente getGemeente() {
        return gemeente;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<Vluchteling> getGehuisvesteVluchtelingen() {
        // Een defensieve, niet-wijzigbare kopie voorkomt externe lijstmutaties.
        return List.copyOf(gehuisvesteVluchtelingen);
    }
}
