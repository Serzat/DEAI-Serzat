//AZC'nin içindeki mesaj kutusu. Yeni bir güncelleme olduğunda anında buraya düşecek.

package observer;

import java.util.ArrayList;
import java.util.List;

public class Berichtenbox implements DossierObserver {
    private List<Bericht> ongelezenBerichten = new ArrayList<>();

    @Override
    public void update(Bericht bericht) {
        ongelezenBerichten.add(bericht);
        System.out.println("🔔 BİLDİRİM: Berichtenbox'a yeni mesaj düştü -> " + bericht.getInhoud());
    }

    public List<Bericht> getOngelezenBerichten() {
        return ongelezenBerichten;
    }
}