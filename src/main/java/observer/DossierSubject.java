package observer;

/** Contract voor een subject binnen het Observer Pattern. */
public interface DossierSubject {
    void voegObserverToe(DossierObserver observer);

    void verwijderObserver(DossierObserver observer);

    void stuurNotificatie(Bericht bericht);
}
