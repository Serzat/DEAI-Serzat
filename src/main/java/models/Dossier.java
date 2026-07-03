package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import observer.Bericht;
import observer.DossierObserver;
import observer.DossierSubject;

import static util.Validatie.vereisTekst;

public final class Dossier implements DossierSubject {
    private String uitspraak = "geen";
    private final List<DossierObserver> observers = new ArrayList<>();
    private final Vluchteling eigenaar;

    public Dossier(Vluchteling eigenaar) {
        this.eigenaar = Objects.requireNonNull(eigenaar, "eigenaar mag niet null zijn.");
    }

    public void setUitspraak(String uitspraak) {
        String nieuweUitspraak = vereisTekst(uitspraak, "uitspraak");
        if (this.uitspraak.equals(nieuweUitspraak)) {
            return;
        }
        this.uitspraak = nieuweUitspraak;
        stuurNotificatie(new Bericht(
                eigenaar,
                "Dossierupdate",
                eigenaar.getNaam() + " - Nieuwe uitspraak in dossier: " + nieuweUitspraak));
    }

    public String getUitspraak() {
        return uitspraak;
    }

    @Override
    public void voegObserverToe(DossierObserver observer) {
        Objects.requireNonNull(observer, "observer mag niet null zijn.");
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void verwijderObserver(DossierObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void stuurNotificatie(Bericht bericht) {
        Objects.requireNonNull(bericht, "bericht mag niet null zijn.");
        for (DossierObserver observer : List.copyOf(observers)) {
            observer.update(bericht);
        }
    }

}
