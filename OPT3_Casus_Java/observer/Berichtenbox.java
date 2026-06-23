package observer;

import java.util.ArrayList;
import java.util.List;

public class Berichtenbox implements DossierObserver {
    private List<Bericht> ongelezenBerichten = new ArrayList<>();

    @Override
    public void update(Bericht bericht) {
        ongelezenBerichten.add(bericht);
        System.out.println("MELDING: Nieuw bericht in Berichtenbox -> " + bericht.getInhoud());
    }

    public List<Bericht> getOngelezenBerichten() {
        return ongelezenBerichten;
    }
}