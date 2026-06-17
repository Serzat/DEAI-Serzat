//Bu bizim şablonumuz. Bütün yerleştirme algoritmaları bu arayüze (Interface) uymak zorunda.
package strategy;

import models.Gemeente;
import java.util.List;

public interface PlaatsingsStrategie {
    Gemeente kiesGemeente(List<Gemeente> gemeentes);
}