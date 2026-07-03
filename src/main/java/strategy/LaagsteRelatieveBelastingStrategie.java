package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

public final class LaagsteRelatieveBelastingStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        Gemeente gekozen = null;
        double laagsteBelasting = Double.POSITIVE_INFINITY;

        for (Gemeente gemeente : gemeentes) {
            if (gemeente == null || gemeente.getVrijePlaatsen() <= 0) {
                continue;
            }

            double belasting = gemeente.getRelatieveBelasting();
            boolean lagereBelasting = belasting < laagsteBelasting;
            boolean gelijkeBelastingMaarMeerRuimte = gekozen != null
                    && Double.compare(belasting, laagsteBelasting) == 0
                    && gemeente.getVrijePlaatsen() > gekozen.getVrijePlaatsen();

            if (lagereBelasting || gelijkeBelastingMaarMeerRuimte) {
                gekozen = gemeente;
                laagsteBelasting = belasting;
            }
        }
        return gekozen;
    }
}
