package app;

import java.util.List;
import models.AZC;
import models.AZCMedewerker;
import models.Beheerder;
import models.COAMedewerker;
import models.Gemeente;
import models.Land;
import models.Vluchteling;
import observer.Bericht;
import observer.Berichtenbox;
import strategy.HoogsteVrijePlaatsenStrategie;
import template.ActieVerwerker;
import template.VluchtelingPlaatsingActie;
import template.VluchtelingRegistratieActie;

/**
 * Startpunt van de demonstratieapplicatie.
 *
 * <p>De main-methode laat achtereenvolgens het Strategy Pattern,
 * Template Method Pattern en Observer Pattern zien. Tot slot wordt een
 * eenvoudige managementrapportage gegenereerd.</p>
 */
public final class Main {
    private Main() {
        // Utility/startklasse: er worden geen Main-objecten aangemaakt.
    }

    public static void main(String[] args) {
        System.out.println("--- COA-SYSTEEM WORDT GESTART ---\n");

        // 1. Basisgegevens voor de demonstratie aanmaken.
        Land syrie = new Land("Syrië", false);
        Gemeente denHaag = new Gemeente("Den Haag", 550_000, 100);
        Gemeente amsterdam = new Gemeente("Amsterdam", 900_000, 50);
        List<Gemeente> gemeentes = List.of(denHaag, amsterdam);

        // Een AZC registreert zichzelf tijdens de constructie bij zijn gemeente.
        new AZC("AZC Den Haag Centrum", "Kerkstraat", "1", "2514 AB", denHaag);
        new AZC("AZC Amsterdam West", "Sloterdijk", "12", "1043 NX", amsterdam);

        Vluchteling vluchteling = new Vluchteling("Ahmed Al-Fahad", syrie);
        COAMedewerker coaMedewerker = new COAMedewerker("Selin");
        AZCMedewerker azcMedewerker = new AZCMedewerker("Johan");
        Beheerder beheerder = new Beheerder("Willem");

        // 2. Strategy Pattern: het selectie-algoritme is tijdens runtime verwisselbaar.
        System.out.println("--- 1. STRATEGY PATTERN ---");
        Gemeente gekozenGemeente = coaMedewerker.voerAutomatischePlaatsing(
                gemeentes, new HoogsteVrijePlaatsenStrategie());
        System.out.println("Gekozen gemeente: "
                + (gekozenGemeente == null ? "geen beschikbare gemeente" : gekozenGemeente.getNaam()));

        // 3. Template Method Pattern: dezelfde vaste procesvolgorde voor iedere actie.
        System.out.println("\n--- 2. TEMPLATE METHOD PATTERN ---");
        ActieVerwerker registratie = new VluchtelingRegistratieActie(vluchteling);
        registratie.verwerkActie();

        // Gebruik daadwerkelijk de door de strategie gekozen gemeente en dus geen hardcoded AZC.
        if (gekozenGemeente != null && !gekozenGemeente.getAzcs().isEmpty()) {
            AZC gekozenAZC = gekozenGemeente.getAzcs().get(0);
            ActieVerwerker plaatsing = new VluchtelingPlaatsingActie(vluchteling, gekozenAZC);
            plaatsing.verwerkActie();
        }

        // 4. Observer Pattern: de berichtenbox ontvangt automatisch een dossierwijziging.
        System.out.println("\n--- 3. OBSERVER PATTERN ---");
        Berichtenbox berichtenbox = new Berichtenbox();
        vluchteling.getDossier().voegObserverToe(berichtenbox);
        vluchteling.getDossier().setUitspraak("geaccepteerd");

        List<Bericht> ongelezen = berichtenbox.getOngelezenBerichten();
        if (!ongelezen.isEmpty()) {
            azcMedewerker.verwerkBericht(ongelezen.get(0), "Kamer 204 / Blok B");
        }
        System.out.println("Ongelezen berichten na verwerking: "
                + berichtenbox.getOngelezenBerichten().size());

        // 5. Overzicht van de actuele toestand van alle gemeentes en AZC's.
        System.out.println("\n--- 4. RAPPORTAGE ---");
        beheerder.vraagRapportageOp(gemeentes);
    }
}
