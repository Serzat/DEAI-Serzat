package models;

import java.util.List;
import java.util.Objects;
import strategy.PlaatsingsContext;
import strategy.PlaatsingsStrategie;

import static util.Validatie.vereisTekst;

public final class COAMedewerker {
    private final String naam;
    private final PlaatsingsContext context = new PlaatsingsContext();

    public COAMedewerker(String naam) {
        this.naam = vereisTekst(naam, "naam");
    }

    public Gemeente voerAutomatischePlaatsing(
            List<Gemeente> gemeentes,
            PlaatsingsStrategie strategie) {
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");
        Objects.requireNonNull(strategie, "strategie mag niet null zijn.");

        System.out.println("Systeem: COA-medewerker " + naam
                + " voert het automatische plaatsingsproces uit.");
        context.setStrategie(strategie);
        return context.voerPlaatsingUit(gemeentes);
    }

}
