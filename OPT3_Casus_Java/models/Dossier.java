//Mültecinin pasaport durumu, başvurusu ve dava süreçlerini takip ettiğimiz yer. (Observer Patterni kurgularken bu sınıfta bir değişiklik olduğunda bildirim göndereceğiz).
package models;

import observer.DossierSubject;
import observer.DossierObserver;
import observer.Bericht;
import java.util.ArrayList;
import java.util.List;

public class Dossier implements DossierSubject {
    private String uitspraak;
    private List<DossierObserver> observers = new ArrayList<>();
    private Vluchteling eigenaar;

    public Dossier(Vluchteling eigenaar) {
        this.eigenaar = eigenaar;
        this.uitspraak = "geen";
    }

    public void setUitspraak(String uitspraak) {
        this.uitspraak = uitspraak;
        // Dosyada bir gelişme olduğunda sisteme otomatik bildirim fırlatıyoruz!
        stuurNotificatie(new Bericht(eigenaar, "Dossier Update", eigenaar.getNaam() + " adlı mültecinin yeni dosya kararı: " + uitspraak));
    }

    public String getUitspraak() { return uitspraak; }

    @Override
    public void voegObserverToe(DossierObserver observer) { observers.add(observer); }

    @Override
    public void verwijderObserver(DossierObserver observer) { observers.remove(observer); }

    @Override
    public void stuurNotificatie(Bericht bericht) {
        for (DossierObserver obs : observers) {
            obs.update(bericht);
        }
    }
}
