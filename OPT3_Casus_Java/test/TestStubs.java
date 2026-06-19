package test;

import observer.Bericht;
import observer.DossierObserver;
import java.util.ArrayList;
import java.util.List;

// Rubrik: Mocks of stubs gebruikt
public class TestStubs {
    
    // Stub Observer: Ekrana yazı basmak yerine mesajları liste içinde toplayan sahte bir mesaj kutusu.
    public static class BerichtenboxStub implements DossierObserver {
        private List<String> logMessages = new ArrayList<>();

        @Override
        public void update(Bericht bericht) {
            logMessages.add(bericht.getInhoud());
        }

        public List<String> getLogMessages() { 
            return logMessages; 
        }
    }
}