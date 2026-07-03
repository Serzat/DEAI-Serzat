package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Concrete observer die ontvangen dossierberichten bewaart. */
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
        // In deze casus betekent 'ongelezen' dat het bericht nog niet is verwerkt.
        return berichten.stream()
                .filter(bericht -> !bericht.isVerwerkt())
                .toList();
    }

    public List<Bericht> getAlleBerichten() {
        // De volledige historie blijft beschikbaar, maar is extern niet wijzigbaar.
        return List.copyOf(berichten);
    }
}
