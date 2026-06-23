package strategy;

import models.Gemeente;
import java.util.List;

public class LaagsteRelatieveBelastingStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        // Variable to hold the winning municipality. Starts empty.
        Gemeente gekozen = null;
        // Creates a tracker for the LOWEST burden found so far. 
        // We start this at the absolute MAXIMUM possible double value (Double.MAX_VALUE).
        // Why? So that the very first municipality we check is guaranteed to be lower than this,
        // and thus becomes the first "winner" to beat.
        double minBelasting = Double.MAX_VALUE;
        // Loops through every municipality in the provided list.
        for (Gemeente g : gemeentes) {
            // IF the current municipality's burden is LOWER than our current minimum...
            if (g.getRelatieveBelasting() < minBelasting) {
                // ...update our tracker to this new, lower burden value.
                minBelasting = g.getRelatieveBelasting();
                // ...and set this municipality as our current winner.
                gekozen = g;
            }
        }
        return gekozen; 
    }
}