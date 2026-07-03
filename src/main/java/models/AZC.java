package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static util.Validatie.vereisTekst;

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
        gemeente.voegAZCToe(this);
    }

    public void voegVluchtelingToe(Vluchteling vluchteling) {
        Objects.requireNonNull(vluchteling, "vluchteling mag niet null zijn.");
        if (gehuisvesteVluchtelingen.contains(vluchteling)) {
            return;
        }
        gemeente.voegVluchtelingToe();
        gehuisvesteVluchtelingen.add(vluchteling);
    }

    public void verwijderVluchteling(Vluchteling vluchteling) {
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
        return List.copyOf(gehuisvesteVluchtelingen);
    }

}
