// Defines the package location.
package strategy;

import java.util.List;
import models.Gemeente;

// This is the  (Interface) for our Strategy Pattern.
// Every placement strategy receives the same list of municipalities 
// and returns exactly one suitable municipality, or null if nothing fits.
public interface PlaatsingsStrategie {
    
    // The required method that all concrete strategy classes must implement.
    Gemeente kiesGemeente(List<Gemeente> gemeentes);
}