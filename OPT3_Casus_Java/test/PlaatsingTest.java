package test;
import static org.junit.Assert.*;
import org.junit.Test;


import models.*;
import strategy.*;
import observer.Bericht;
import java.util.ArrayList;
import java.util.List;


public class PlaatsingTest {

    // Marks the following method as a test case for JUnit framework
    @Test

    // Declares the public method to test the placement behavior with an empty municipality list
    public void testLegeGemeenteLijst() {

        // Creates a new placement context instance to run the strategy
        PlaatsingsContext context = new PlaatsingsContext();

        // Sets the strategy to select the municipality with the highest number of free places
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());
        
        // Creates a new empty list to hold Gemeente objects
        List<Gemeente> legeLijst = new ArrayList<>();
        
        // Boundary Value Test: We expect null to be returned when the list is completely empty
        // Asserts that the result of the placement execution is null, providing a custom failure message
        assertNull("Lege lijst moet null retourneren.", context.voerPlaatsingUit(legeLijst));
    }

    // Marks the following method as a test case for JUnit framework
    @Test
    // Declares the public method to test the placement behavior when a municipality has full capacity
    public void testVolleCapaciteit() {
        // Creates a new placement context instance to run the strategy
        PlaatsingsContext context = new PlaatsingsContext();
        // Sets the strategy to select the municipality with the highest number of free places
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());
        
        // Creates a new list to hold Gemeente objects
        List<Gemeente> volleLijst = new ArrayList<>();
        // Adds a new Gemeente object (Rotterdam) with 0 available places to the list
        volleLijst.add(new Gemeente("Rotterdam", 600000, 0));
        
        // Even if the capacity is 0, we expect the object itself to be returned because it is the only option available
        // Asserts that the result of the placement execution is not null, providing a custom failure message
        assertNotNull("Zelfs bij volle capaciteit moet een object geretourneerd worden.", context.voerPlaatsingUit(volleLijst));
    }

    // Marks the following method as a test case for JUnit framework
    @Test
    // Declares the public method to test the observer notification system using a stub
    public void testObserverMetStub() {
        // Creates a new refugee object named Test Ali originating from Syria (marked as not safe)
        Vluchteling testVluchteling = new Vluchteling("Test Ali", new Land("Syrië", false));
        // Creates a new fake inbox (stub) to intercept the notifications without printing them
        TestStubs.BerichtenboxStub stubBox = new TestStubs.BerichtenboxStub();
        
        // Subscribes the fake inbox to the refugee's dossier so it can listen for updates
        testVluchteling.getDossier().voegObserverToe(stubBox);
        // Updates the dossier status to rejected, which should automatically trigger a notification
        testVluchteling.getDossier().setUitspraak("afgewezen");

        // Verifies that the message was actually delivered to the stub (Proof of proper Stub usage)
        // Asserts that the logMessages list contains exactly 1 item
        assertEquals("Observer heeft het bericht niet ontvangen.", 1, stubBox.getLogMessages().size());
        
        // Asserts that the content of the received message contains the exact word 'afgewezen'
        assertTrue("Inhoud van het bericht is onjuist.", stubBox.getLogMessages().get(0).contains("afgewezen"));
    }

    // Marks the following method as a test case for JUnit framework
    @Test
    // Declares the public method to test the new message processing and room assignment feature
    public void testBerichtVerwerking() {
        // Creates a new dummy refugee object for the processing test
        Vluchteling dummyVluchteling = new Vluchteling("Test Vluchteling", new Land("TestLand", true));
        // Creates a new message object related to the dummy refugee
        Bericht bericht = new Bericht(dummyVluchteling, "Plaatsing", "Nieuwe plaatsing test");
        
        // Asserts that the message is initially marked as not processed (false)
        assertFalse("Bericht moet initieel onverwerkt zijn.", bericht.isVerwerkt());
        
        // Processes the message and assigns a specific room number to it
        bericht.markeerAlsVerwerkt("Kamer 101");
        
        // Asserts that the message is now marked as processed (true) after the action
        assertTrue("Bericht moet verwerkt zijn na actie.", bericht.isVerwerkt());
        // Asserts that the assigned room number perfectly matches the expected value
        assertEquals("Kamer moet correct zijn toegewezen.", "Kamer 101", bericht.getKamer());
    }
}