package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Berichtenbox implements DossierObserver {
    private final List<Bericht> berichten = new ArrayList<>();

    @Override
    public void update(Bericht bericht) {
        Bericht ontvangenBericht = Objects.requireNonNull(
                bericht, "bericht mag niet null zijn.");
        berichten.add(ontvangenBericht);
        System.out.println("MELDING: Nieuw bericht in berichtenbox -> "
                + ontvangenBericht.getInhoud());
    }

    public List<Bericht> getOngelezenBerichten() {
        return berichten.stream()
                .filter(bericht -> !bericht.isVerwerkt())
                .toList();
    }

    public List<Bericht> getAlleBerichten() {
        return List.copyOf(berichten);
    }
}
