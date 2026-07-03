package observer;

/** Contract voor objecten die dossierwijzigingen willen ontvangen. */
public interface DossierObserver {
    void update(Bericht bericht);
}
