package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

// The Context class within the Strategy Pattern that executes the active algorithm.
public final class PlaatsingsContext {
    
    // Holds the currently active strategy.
    private PlaatsingsStrategie actieveStrategie;

    // Setter method to dynamically plug in a specific algorithm.
    public void setStrategie(PlaatsingsStrategie strategie) {
        // Ensures the provided strategy is not null before assigning it.
        this.actieveStrategie = Objects.requireNonNull(
                strategie, "strategie mag niet null zijn.");
    }

    // The main execution method that triggers the selected strategy.
    public Gemeente voerPlaatsingUit(List<Gemeente> gemeentes) {
        
        // Safety check: If no strategy was set, crash intentionally with a clear error message.
        if (actieveStrategie == null) {
            throw new IllegalStateException("Er is geen plaatsingsstrategie geselecteerd.");
        }
        
        // Safety check: Validates the input list before passing it to the strategy.
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");
        
        // Delegates the actual selection work to the active strategy class.
        return actieveStrategie.kiesGemeente(gemeentes);
    }
}