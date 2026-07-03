package test;

import java.util.ArrayList;
import java.util.List;
import models.AZC;
import models.Gemeente;
import models.Land;
import models.Vluchteling;
import observer.Bericht;
import observer.Berichtenbox;
import org.junit.Test;
import strategy.HoogsteVrijePlaatsenStrategie;
import strategy.LaagsteRelatieveBelastingStrategie;
import strategy.PlaatsingsContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class PlaatsingTest {
    @Test
    public void legeGemeenteLijstGeeftGeenPlaatsing() {
        PlaatsingsContext context = new PlaatsingsContext();
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());

        assertNull(context.voerPlaatsingUit(new ArrayList<>()));
    }

    @Test
    public void volleGemeenteWordtNietGeselecteerd() {
        Gemeente rotterdam = new Gemeente("Rotterdam", 600_000, 0);
        PlaatsingsContext context = new PlaatsingsContext();
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());

        assertNull(context.voerPlaatsingUit(List.of(rotterdam)));
    }

    @Test
    public void hoogsteVrijePlaatsenSelecteertJuisteGemeente() {
        Gemeente denHaag = new Gemeente("Den Haag", 550_000, 25);
        Gemeente amsterdam = new Gemeente("Amsterdam", 900_000, 50);
        PlaatsingsContext context = new PlaatsingsContext();
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());

        assertSame(amsterdam, context.voerPlaatsingUit(List.of(denHaag, amsterdam)));
    }

    @Test
    public void laagsteBelastingSlaatVolleGemeenteOver() {
        Gemeente vol = new Gemeente("Vol", 100_000, 1);
        Gemeente beschikbaar = new Gemeente("Beschikbaar", 100_000, 2);
        AZC volAzc = new AZC("AZC Vol", "Straat", "1", "1000 AA", vol);
        Vluchteling eerste = new Vluchteling("Eerste", new Land("Land", true));
        eerste.plaatsInAZC(volAzc);

        PlaatsingsContext context = new PlaatsingsContext();
        context.setStrategie(new LaagsteRelatieveBelastingStrategie());

        assertSame(beschikbaar, context.voerPlaatsingUit(List.of(vol, beschikbaar)));
    }

    @Test
    public void plaatsingHoudtBeideKantenVanRelatieBij() {
        Gemeente gemeente = new Gemeente("Delft", 100_000, 2);
        AZC azc = new AZC("AZC Delft", "Markt", "1", "2611 AA", gemeente);
        Vluchteling vluchteling = new Vluchteling("Testpersoon", new Land("Testland", true));

        vluchteling.plaatsInAZC(azc);

        assertSame(azc, vluchteling.getHuidigAZC());
        assertTrue(azc.getGehuisvesteVluchtelingen().contains(vluchteling));
        assertEquals(1, gemeente.getAantalGeplaatsteVluchtelingen());
        assertEquals(1, gemeente.getVrijePlaatsen());
    }

    @Test
    public void verplaatsingVerwijdertOudeRegistratie() {
        Gemeente eersteGemeente = new Gemeente("Eerste gemeente", 100_000, 2);
        Gemeente tweedeGemeente = new Gemeente("Tweede gemeente", 100_000, 2);
        AZC eersteAzc = new AZC("Eerste AZC", "Straat", "1", "1000 AA", eersteGemeente);
        AZC tweedeAzc = new AZC("Tweede AZC", "Straat", "2", "2000 AA", tweedeGemeente);
        Vluchteling vluchteling = new Vluchteling("Testpersoon", new Land("Testland", true));

        vluchteling.plaatsInAZC(eersteAzc);
        vluchteling.plaatsInAZC(tweedeAzc);

        assertFalse(eersteAzc.getGehuisvesteVluchtelingen().contains(vluchteling));
        assertTrue(tweedeAzc.getGehuisvesteVluchtelingen().contains(vluchteling));
        assertEquals(0, eersteGemeente.getAantalGeplaatsteVluchtelingen());
        assertEquals(1, tweedeGemeente.getAantalGeplaatsteVluchtelingen());
    }


    @Test
    public void verhuizingBinnenVolleGemeenteBlijftMogelijk() {
        Gemeente gemeente = new Gemeente("Gemeente", 100_000, 1);
        AZC eersteAzc = new AZC("Eerste AZC", "Straat", "1", "1000 AA", gemeente);
        AZC tweedeAzc = new AZC("Tweede AZC", "Straat", "2", "1000 AB", gemeente);
        Vluchteling vluchteling = new Vluchteling("Testpersoon", new Land("Testland", true));

        vluchteling.plaatsInAZC(eersteAzc);
        vluchteling.plaatsInAZC(tweedeAzc);

        assertSame(tweedeAzc, vluchteling.getHuidigAZC());
        assertFalse(eersteAzc.getGehuisvesteVluchtelingen().contains(vluchteling));
        assertTrue(tweedeAzc.getGehuisvesteVluchtelingen().contains(vluchteling));
        assertEquals(1, gemeente.getAantalGeplaatsteVluchtelingen());
        assertEquals(0, gemeente.getVrijePlaatsen());
    }

    @Test
    public void mislukteVerhuizingBehoudtOudePlaatsing() {
        Gemeente vertrek = new Gemeente("Vertrek", 100_000, 1);
        Gemeente volBestemming = new Gemeente("Bestemming", 100_000, 1);
        AZC vertrekAzc = new AZC("Vertrek AZC", "Straat", "1", "1000 AA", vertrek);
        AZC bestemmingAzc = new AZC("Bestemming AZC", "Straat", "2", "2000 AA", volBestemming);
        Vluchteling teVerhuizen = new Vluchteling("Te verhuizen", new Land("Land", true));
        Vluchteling bezetter = new Vluchteling("Bezetter", new Land("Land", true));
        teVerhuizen.plaatsInAZC(vertrekAzc);
        bezetter.plaatsInAZC(bestemmingAzc);

        assertThrows(IllegalStateException.class, () -> teVerhuizen.plaatsInAZC(bestemmingAzc));

        assertSame(vertrekAzc, teVerhuizen.getHuidigAZC());
        assertTrue(vertrekAzc.getGehuisvesteVluchtelingen().contains(teVerhuizen));
        assertFalse(bestemmingAzc.getGehuisvesteVluchtelingen().contains(teVerhuizen));
        assertEquals(1, vertrek.getAantalGeplaatsteVluchtelingen());
        assertEquals(1, volBestemming.getAantalGeplaatsteVluchtelingen());
    }

    @Test
    public void capaciteitKanNietWordenOverschreden() {
        Gemeente gemeente = new Gemeente("Kleine gemeente", 10_000, 1);
        AZC azc = new AZC("Klein AZC", "Straat", "1", "1000 AA", gemeente);
        new Vluchteling("Eerste", new Land("Land", true)).plaatsInAZC(azc);
        Vluchteling tweede = new Vluchteling("Tweede", new Land("Land", true));

        assertThrows(IllegalStateException.class, () -> tweede.plaatsInAZC(azc));
        assertNull(tweede.getHuidigAZC());
        assertEquals(1, azc.getGehuisvesteVluchtelingen().size());
    }

    @Test
    public void observerOntvangtEenBerichtBijStatuswijziging() {
        Vluchteling vluchteling = new Vluchteling("Test Ali", new Land("Syrië", false));
        TestStubs.BerichtenboxStub stub = new TestStubs.BerichtenboxStub();
        vluchteling.getDossier().voegObserverToe(stub);

        vluchteling.getDossier().setUitspraak("afgewezen");

        assertEquals(1, stub.getLogMessages().size());
        assertTrue(stub.getLogMessages().get(0).contains("afgewezen"));
    }

    @Test
    public void dezelfdeUitspraakStuurtGeenDubbeleNotificatie() {
        Vluchteling vluchteling = new Vluchteling("Test Ali", new Land("Syrië", false));
        TestStubs.BerichtenboxStub stub = new TestStubs.BerichtenboxStub();
        vluchteling.getDossier().voegObserverToe(stub);

        vluchteling.getDossier().setUitspraak("geaccepteerd");
        vluchteling.getDossier().setUitspraak("geaccepteerd");

        assertEquals(1, stub.getLogMessages().size());
    }

    @Test
    public void verwerktBerichtIsNietMeerOngelezen() {
        Vluchteling vluchteling = new Vluchteling("Testpersoon", new Land("Testland", true));
        Berichtenbox berichtenbox = new Berichtenbox();
        Bericht bericht = new Bericht(vluchteling, "Plaatsing", "Nieuwe plaatsing");
        berichtenbox.update(bericht);

        assertEquals(1, berichtenbox.getOngelezenBerichten().size());
        bericht.markeerAlsVerwerkt("Kamer 101");

        assertTrue(bericht.isVerwerkt());
        assertEquals("Kamer 101", bericht.getKamer());
        assertTrue(berichtenbox.getOngelezenBerichten().isEmpty());
        assertEquals(1, berichtenbox.getAlleBerichten().size());
    }

    @Test
    public void geretourneerdeCollectiesZijnNietWijzigbaar() {
        Gemeente gemeente = new Gemeente("Delft", 100_000, 1);
        new AZC("AZC Delft", "Markt", "1", "2611 AA", gemeente);

        assertThrows(UnsupportedOperationException.class, () -> gemeente.getAzcs().clear());
    }
}
