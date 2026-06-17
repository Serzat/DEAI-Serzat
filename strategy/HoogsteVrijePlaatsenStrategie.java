//Rubric'te istenen ilk kural: En çok boş yeri olan belediyeyi seçen algoritma.
package strategy;

import models.Gemeente;
import java.util.List;

public class HoogsteVrijePlaatsenStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        Gemeente gekozen = null;
        int maxVrijePlaatsen = -1;

        for (Gemeente g : gemeentes) {
            if (g.getVrijePlaatsen() > maxVrijePlaatsen) {
                maxVrijePlaatsen = g.getVrijePlaatsen();
                gekozen = g;
            }
        }
        return gekozen; 
    }
}