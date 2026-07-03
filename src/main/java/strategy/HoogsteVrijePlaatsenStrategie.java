package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

public final class HoogsteVrijePlaatsenStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        Gemeente gekozen = null;
        int meesteVrijePlaatsen = 0;

        for (Gemeente gemeente : gemeentes) {
            if (gemeente != null && gemeente.getVrijePlaatsen() > meesteVrijePlaatsen) {
                gekozen = gemeente;
                meesteVrijePlaatsen = gemeente.getVrijePlaatsen();
            }
        }
        return gekozen;
    }
}
