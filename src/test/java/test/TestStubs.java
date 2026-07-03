package test;

import java.util.ArrayList;
import java.util.List;
import observer.Bericht;
import observer.DossierObserver;

public final class TestStubs {
    private TestStubs() {
    }

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
