//Uygulamamızın hangi stratejiyi kullanacağına karar verip çalıştırdığı ana merkez (Context) burasıdır. Sınıfımız algoritmanın içeriğini bilmez, sadece çalıştırır.
package strategy;

import models.Gemeente;
import java.util.List;

public class PlaatsingsContext {
    // A private variable that holds the CURRENTLY selected strategy.
    // Notice its type is the Interface ('PlaatsingsStrategie'), meaning it can 
    // hold ANY class that implements that interface.
    private PlaatsingsStrategie actieveStrategie;
    // Setter method: Used to plug a specific strategy (like a cartridge) into our remote control.
    // Sets the active placement algorithm that the system will use to make its decision.
    public void setStrategie(PlaatsingsStrategie strategie) {
        // Saves the chosen strategy into the object's memory.
        this.actieveStrategie = strategie;
    }
    // The main execution method. It takes the list of municipalities and asks 
    // the active strategy to pick one.
    public Gemeente voerPlaatsingUit(List<Gemeente> gemeentes) {
        // Safety check: If someone tries to run the placement BEFORE choosing a strategy...
        if (actieveStrategie == null) {
            // ...crash the process intentionally and throw a clear error message.
            // This prevents the system from silently failing.
            throw new IllegalStateException("Fout: Er is geen plaatsingsstrategie geselecteerd.!");
        }
        // If a strategy is plugged in, let it do its job. 
        // We don't care WHICH strategy it is, we just call its 'kiesGemeente' method.
        return actieveStrategie.kiesGemeente(gemeentes);
    }
}