//Bu bizim şablonumuz. Bütün yerleştirme algoritmaları bu arayüze (Interface) uymak zorunda.
package strategy;

import models.Gemeente;
import java.util.List;
// Declares a public INTERFACE. This is a contract. Any class that implements this 
// interface MUST contain the exact method defined below.
public interface PlaatsingsStrategie {
    // It forces every placement algorithm to have a method that takes a list of municipalities
// and returns exactly one chosen municipality as the winner.
    Gemeente kiesGemeente(List<Gemeente> gemeentes);
}