//Uygulamamızın hangi stratejiyi kullanacağına karar verip çalıştırdığı ana merkez (Context) burasıdır. Sınıfımız algoritmanın içeriğini bilmez, sadece çalıştırır.
package strategy;

import models.Gemeente;
import java.util.List;

public class PlaatsingsContext {
    private PlaatsingsStrategie actieveStrategie;

    public void setStrategie(PlaatsingsStrategie strategie) {
        this.actieveStrategie = strategie;
    }

    public Gemeente voerPlaatsingUit(List<Gemeente> gemeentes) {
        if (actieveStrategie == null) {
            throw new IllegalStateException("Hata: Herhangi bir yerleştirme stratejisi seçilmedi!");
        }
        return actieveStrategie.kiesGemeente(gemeentes);
    }
}