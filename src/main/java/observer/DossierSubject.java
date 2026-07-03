package observer;

public interface DossierSubject {
    void voegObserverToe(DossierObserver observer);

    void verwijderObserver(DossierObserver observer);

    void stuurNotificatie(Bericht bericht);
}
