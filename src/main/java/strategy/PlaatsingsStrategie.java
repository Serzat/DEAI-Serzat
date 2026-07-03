package strategy;

import java.util.List;
import models.Gemeente;

/**
 * Strategy-contract. Iedere plaatsingsstrategie ontvangt dezelfde lijst met
 * gemeentes en retourneert één geschikte gemeente of null wanneer niets past.
 */
public interface PlaatsingsStrategie {
    Gemeente kiesGemeente(List<Gemeente> gemeentes);
}
