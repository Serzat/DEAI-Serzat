package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

/**
 * Selecteert de beschikbare gemeente met de laagste verhouding tussen geplaatste
 * vluchtelingen en inwoners. Bij gelijke belasting wint de gemeente met meer ruimte.
 */
public final class LaagsteRelatieveBelastingStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        Gemeente gekozen = null;
        double laagsteBelasting = Double.POSITIVE_INFINITY;

        for (Gemeente gemeente : gemeentes) {
            if (gemeente == null || gemeente.getVrijePlaatsen() <= 0) {
                // Null-items en volle gemeentes zijn geen geldige kandidaat.
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
