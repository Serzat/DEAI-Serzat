package observer;

public interface DossierSubject {
    void voegObserverToe(DossierObserver observer); // attach 
    void verwijderObserver(DossierObserver observer); // detach 
    void stuurNotificatie(Bericht bericht); // notify 
}