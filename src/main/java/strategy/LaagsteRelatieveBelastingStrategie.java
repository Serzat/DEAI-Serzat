package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

// Selects the municipality with the lowest ratio of placed refugees to inhabitants.
// If the burden is equal, the municipality with more available space wins.
public final class LaagsteRelatieveBelastingStrategie implements PlaatsingsStrategie {
    
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        // Defensive programming: Validates the input list.
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        // Variable to hold the winning municipality.
        Gemeente gekozen = null;
        
        // Starts with the absolute maximum possible value so the first valid municipality will easily beat it.
        double laagsteBelasting = Double.POSITIVE_INFINITY;

        // Loops through the list of municipalities.
        for (Gemeente gemeente : gemeentes) {
            
            // Instantly skips invalid data or municipalities that are completely full (0 or less capacity).
            if (gemeente == null || gemeente.getVrijePlaatsen() <= 0) {
                continue;
            }

            // Calculates the relative burden of the current municipality.
            double belasting = gemeente.getRelatieveBelasting();
            
            // Condition 1: Is this burden strictly lower than our current lowest record?
            boolean lagereBelasting = belasting < laagsteBelasting;
            
            // Condition 2
            // 1. Ensure a municipality has already been selected.
            // 2. Check whether both municipalities have the same workload ratio.
            // 3. If they do, prefer the municipality with more available places.
            boolean gelijkeBelastingMaarMeerRuimte = gekozen != null
                    && Double.compare(belasting, laagsteBelasting) == 0
                    && gemeente.getVrijePlaatsen() > gekozen.getVrijePlaatsen();

            // If either condition is true, this municipality becomes the new winner.
            if (lagereBelasting || gelijkeBelastingMaarMeerRuimte) {
                gekozen = gemeente;
                laagsteBelasting = belasting;
            }
        }
        
        // Returns the municipality with the lowest burden (or the tie-breaker winner).
        return gekozen;
    }
}