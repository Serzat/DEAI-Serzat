package test;

// JUnit 4 kütüphane importları
import static org.junit.Assert.*;
import org.junit.Test;

import models.*;
import strategy.*;
import java.util.ArrayList;
import java.util.List;

public class PlaatsingTest {

    @Test
    public void testLegeGemeenteLijst() {
        PlaatsingsContext context = new PlaatsingsContext();
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());
        
        List<Gemeente> legeLijst = new ArrayList<>();
        
        // Boş listede null dönmesini bekliyoruz (Sınır Değeri Testi)
        assertNull("Lege lijst moet null retourneren.", context.voerPlaatsingUit(legeLijst));
    }

    @Test
    public void testVolleCapaciteit() {
        PlaatsingsContext context = new PlaatsingsContext();
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());
        
        List<Gemeente> volleLijst = new ArrayList<>();
        volleLijst.add(new Gemeente("Rotterdam", 600000, 0));
        
        // 0 kapasiteli olsa bile nesnenin kendisinin dönmesini bekliyoruz
        assertNotNull("Zelfs bij volle capaciteit moet een object geretourneerd worden.", context.voerPlaatsingUit(volleLijst));
    }

    @Test
    public void testObserverMetStub() {
        Vluchteling testVluchteling = new Vluchteling("Test Ali", new Land("Syrië", false));
        TestStubs.BerichtenboxStub stubBox = new TestStubs.BerichtenboxStub();
        
        testVluchteling.getDossier().voegObserverToe(stubBox);
        testVluchteling.getDossier().setUitspraak("afgewezen");

        // Mesajın gerçekten iletildiğini doğruluyoruz (Stub Kullanımı)
        assertEquals("Observer heeft het bericht niet ontvangen.", 1, stubBox.getLogMessages().size());
        assertTrue("Inhoud van het bericht is onjuist.", stubBox.getLogMessages().get(0).contains("afgewezen"));
    }
}