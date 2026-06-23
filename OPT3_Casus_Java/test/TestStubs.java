package test;
import observer.Bericht;
import observer.DossierObserver;
import java.util.ArrayList;
import java.util.List;

// Rubric requirement: Mocks or stubs used for testing
// Declares the public class named TestStubs
public class TestStubs {
    
    // Stub Observer: A fake inbox that collects messages in a list instead of printing them to the screen
    // Declares the public static nested class BerichtenboxStub that implements the DossierObserver interface
    public static class BerichtenboxStub implements DossierObserver {
        // Private list variable to store the contents of received messages
        private List<String> logMessages = new ArrayList<>();

        // Overrides the update method from the DossierObserver interface to handle incoming notifications
        @Override
        public void update(Bericht bericht) {
            // Adds the content of the incoming message to the logMessages list for later verification
            logMessages.add(bericht.getInhoud());
        }

        // Public getter method to return the list of collected log messages
        public List<String> getLogMessages() { 
            // Returns the logMessages list so tests can inspect what was received
            return logMessages; 
        }
    }
}