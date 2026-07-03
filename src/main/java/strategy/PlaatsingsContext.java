package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

public final class PlaatsingsContext {
    private PlaatsingsStrategie actieveStrategie;

    public void setStrategie(PlaatsingsStrategie strategie) {
        this.actieveStrategie = Objects.requireNonNull(
                strategie, "strategie mag niet null zijn.");
    }

    public Gemeente voerPlaatsingUit(List<Gemeente> gemeentes) {
        if (actieveStrategie == null) {
            throw new IllegalStateException("Er is geen plaatsingsstrategie geselecteerd.");
        }
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");
        return actieveStrategie.kiesGemeente(gemeentes);
    }
}
