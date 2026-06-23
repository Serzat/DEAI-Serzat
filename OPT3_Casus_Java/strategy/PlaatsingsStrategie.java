
package strategy;

import models.Gemeente;
import java.util.List;

public interface PlaatsingsStrategie {
    
    // It forces every placement algorithm to have a method that takes a list of municipalities
// and returns exactly one chosen municipality as the winner.
    Gemeente kiesGemeente(List<Gemeente> gemeentes);
}