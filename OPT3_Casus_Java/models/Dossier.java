package models;
import observer.DossierSubject;
import observer.DossierObserver;
import observer.Bericht;
import java.util.ArrayList;
import java.util.List;
// Declares Dossier class and implements DossierSubject, meaning it MUST use the publisher methods.
public class Dossier implements DossierSubject {
    // Private text variable to store the legal decision/status of the refugee's case.
    private String uitspraak;
    // A list of  observers who want to be notified when this dossier changes.
    private List<DossierObserver> observers = new ArrayList<>();
    // Private variable holding the Vluchteling (Refugee) object that owns this specific dossier.
    private Vluchteling eigenaar;
    // Constructor method: Creates a dossier and links it to its owner (eigenaar).
    public Dossier(Vluchteling eigenaar) {
        // Private variable holding the Vluchteling (Refugee) object that owns this specific dossier.
        this.eigenaar = eigenaar;
        // Sets the default legal decision to "geen" (none) since the case just started.
        this.uitspraak = "geen";
    }
    // Method to update the legal decision. This is the trigger for our Observer Pattern!
    public void setUitspraak(String uitspraak) {
        // Updates the old decision with the new one passed in the parameter.
        this.uitspraak = uitspraak;
        // Creates a new 'Bericht' (Message) object and fires the stuurNotificatie method to alert everyone.
        stuurNotificatie(new Bericht(eigenaar, "Dossier Update", eigenaar.getNaam() + " - Nieuwe uitspraak in dossier: " + uitspraak));
    }
    // Getter method: Returns the current legal decision status.
    public String getUitspraak() { return uitspraak; }
    // Override means we are fulfilling the contract from the DossierSubject interface.
    // Adds a new subscriber to the observers list.
    @Override
    public void voegObserverToe(DossierObserver observer) { observers.add(observer); }
    // Removes a subscriber from the observers list so they no longer get updates.
    @Override
    public void verwijderObserver(DossierObserver observer) { observers.remove(observer); }
    // The core of the Observer Pattern: Loops through the list of subscribers and notifies them.
    @Override
    public void stuurNotificatie(Bericht bericht) {
        // A "for-each" loop: For every DossierObserver (named 'obs') inside the 'observers' list...
        for (DossierObserver obs : observers) {
            // ...call their specific update() method and hand them the new message (bericht).
            obs.update(bericht);
        }
    }
}



//Mültecinin iltica durumu (geaccepteerd/afgewezen) burada tutulur. İçine kurduğumuz Observer Pattern sayesinde, 
//durumda en ufak bir değişiklik olduğunda abone olan tüm sistemlere otomatik olarak bağırıp haber verir.