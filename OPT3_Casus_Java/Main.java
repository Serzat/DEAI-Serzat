import models.*;
import strategy.*;
import template.*;
import observer.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- COA SYSTEEM WORDT GESTART ---\n");

        // 1. Temel Verilerin Olusturulmasi
        Land syrie = new Land("Syrië", false);
        Gemeente denHaag = new Gemeente("Den Haag", 550000, 100);
        Gemeente amsterdam = new Gemeente("Amsterdam", 900000, 50);

        List<Gemeente> gemeentes = new ArrayList<>();
        gemeentes.add(denHaag);
        gemeentes.add(amsterdam);

        AZC azcDenHaag = new AZC("AZC Den Haag Centrum", "Kerkstraat", "1", "2514AB", denHaag);
        Vluchteling vluchteling = new Vluchteling("Ahmed Al-Fahad", syrie);

        // 2. STRATEGY PATTERN TESTI
        System.out.println("--- 1. STRATEGY PATTERN (Automatische Plaatsing) ---");
        PlaatsingsContext context = new PlaatsingsContext();

        context.setStrategie(new HoogsteVrijePlaatsenStrategie());
        Gemeente gekozen1 = context.voerPlaatsingUit(gemeentes);
        System.out.println("Strategie 1 (Meeste vrije plaatsen): Gekozen Gemeente -> " + (gekozen1 != null ? gekozen1.getNaam() : "Geen"));

        context.setStrategie(new LaagsteRelatieveBelastingStrategie());
        Gemeente gekozen2 = context.voerPlaatsingUit(gemeentes);
        System.out.println("Strategie 2 (Laagste relatieve belasting): Gekozen Gemeente -> " + (gekozen2 != null ? gekozen2.getNaam() : "Geen") + "\n");

        // 3. TEMPLATE METHOD PATTERN TESTI
        System.out.println("--- 2. TEMPLATE METHOD PATTERN (Standaard Actiestappen) ---");
        ActieVerwerker registratieActie = new VluchtelingRegistratieActie(vluchteling);
        registratieActie.verwerkActie();
        System.out.println();

        ActieVerwerker plaatsingsActie = new VluchtelingPlaatsingActie(vluchteling, azcDenHaag);
        plaatsingsActie.verwerkActie();
        System.out.println();

        // 4. OBSERVER PATTERN TESTI
        System.out.println("--- 3. OBSERVER PATTERN (Automatisch Meldingssysteem) ---");
        Berichtenbox azcBerichtenbox = new Berichtenbox();
        vluchteling.getDossier().voegObserverToe(azcBerichtenbox);

        System.out.println("Systeem: Dossier van de vluchteling wordt bijgewerkt (Uitspraak: geaccepteerd)...");
        vluchteling.getDossier().setUitspraak("geaccepteerd");
        System.out.println();

        

        

       
    }
}