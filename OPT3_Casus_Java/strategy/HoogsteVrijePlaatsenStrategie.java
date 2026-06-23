package strategy;

import models.Gemeente;
import java.util.List;

public class HoogsteVrijePlaatsenStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        // Creates a variable to store the winning municipality. Starts as empty (null).
        Gemeente gekozen = null;
        // Creates a tracker for the highest number of free places found so far.
        // Starts at -1 so that even a municipality with 0 free places can beat it initially.
        int maxVrijePlaatsen = -1;
        // A for-each loop: Checks every single 'Gemeente' (named 'g') inside the 'gemeentes' list
        for (Gemeente g : gemeentes) {
            // IF the current municipality (g) has MORE free places than our current maximum...
            if (g.getVrijePlaatsen() > maxVrijePlaatsen) {
                // ...then update our tracker to this new, higher number.
                maxVrijePlaatsen = g.getVrijePlaatsen();
                // ...and set this municipality as our new "winning" choice.
                gekozen = g;
            }
        }
        return gekozen; 
    }
}