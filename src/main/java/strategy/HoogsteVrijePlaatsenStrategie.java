package strategy;

import java.util.List;
import java.util.Objects;
import models.Gemeente;

/** Selecteert de beschikbare gemeente met het hoogste absolute aantal vrije plaatsen. */
public final class HoogsteVrijePlaatsenStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        Gemeente gekozen = null;
        int meesteVrijePlaatsen = 0;

        for (Gemeente gemeente : gemeentes) {
            // Door te starten bij 0 worden volle gemeentes nooit geselecteerd.
            if (gemeente != null && gemeente.getVrijePlaatsen() > meesteVrijePlaatsen) {
                gekozen = gemeente;
                meesteVrijePlaatsen = gemeente.getVrijePlaatsen();
            }
        }
        return gekozen;
    }
}
