package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

// Selects the available municipality with the highest absolute number of free places.
// The 'final' keyword prevents other classes from inheriting and modifying this logic.
public final class HoogsteVrijePlaatsenStrategie implements PlaatsingsStrategie {
    
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        // Defensive programming: Ensures the provided list is not null to prevent system crashes.
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        // Variable to hold the winning municipality. Starts as null.
        Gemeente gekozen = null;
        
        // Starts at 0. This ensures that municipalities with 0 capacity are NEVER selected.
        int meesteVrijePlaatsen = 0;

        // Loops through every municipality in the list.
        for (Gemeente gemeente : gemeentes) {
            
            // Checks if the municipality exists AND if its free places are higher than the current record.
            if (gemeente != null && gemeente.getVrijePlaatsen() > meesteVrijePlaatsen) {
                
                // Updates the winner and the new highest record.
                gekozen = gemeente;
                meesteVrijePlaatsen = gemeente.getVrijePlaatsen();
            }
        }
        
        // Returns the municipality with the absolute most free places.
        return gekozen;
    }
}