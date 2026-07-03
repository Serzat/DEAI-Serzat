package strategy;

import java.util.List;
import models.Gemeente;

public interface PlaatsingsStrategie {
    Gemeente kiesGemeente(List<Gemeente> gemeentes);
}
