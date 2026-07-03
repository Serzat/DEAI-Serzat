package test;

import java.util.ArrayList;
import java.util.List;
import observer.Bericht;
import observer.DossierObserver;

/** Testdoubles die productielogica isoleren zonder externe mockingbibliotheek. */
public final class TestStubs {
    private TestStubs() {
        // Utilityklasse voor geneste teststubs.
    }

    /**
     * Stub-observer die berichtinhoud in het geheugen bewaart in plaats van
     * uitvoer naar het scherm te schrijven.
     */
    public static final class BerichtenboxStub implements DossierObserver {
        private final List<String> logMessages = new ArrayList<>();

        @Override
        public void update(Bericht bericht) {
            logMessages.add(bericht.getInhoud());
        }

        public List<String> getLogMessages() {
            return List.copyOf(logMessages);
        }
    }
}
