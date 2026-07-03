package models;

import static util.Validatie.vereisTekst;

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
