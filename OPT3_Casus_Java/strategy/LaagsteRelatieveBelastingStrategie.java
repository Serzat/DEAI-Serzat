//Rubric'te istenen alternatif kural: Boş yer yetersizse nüfusa göre en az mülteci barındıran (en düşük yoğunluklu) belediyeyi seçen algoritma.
package strategy;

import models.Gemeente;
import java.util.List;

public class LaagsteRelatieveBelastingStrategie implements PlaatsingsStrategie {
    @Override
    public Gemeente kiesGemeente(List<Gemeente> gemeentes) {
        Gemeente gekozen = null;
        double minBelasting = Double.MAX_VALUE;

        for (Gemeente g : gemeentes) {
            if (g.getRelatieveBelasting() < minBelasting) {
                minBelasting = g.getRelatieveBelasting();
                gekozen = g;
            }
        }
        return gekozen; 
    }
}