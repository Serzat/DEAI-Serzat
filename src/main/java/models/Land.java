package models;

import static util.Validatie.vereisTekst;

/** Waardeobject met de naam van een land en de bijbehorende veiligheidsstatus. */
public final class Land {
    private final String naam;
    private final boolean veilig;

    public Land(String naam, boolean veilig) {
        this.naam = vereisTekst(naam, "naam");
        this.veilig = veilig;
    }

    public String getNaam() {
        return naam;
    }

    public boolean isVeilig() {
        return veilig;
    }
}
