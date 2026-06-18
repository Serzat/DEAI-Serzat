import models.*;
import strategy.*;
import template.*;
import observer.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- COA SYSTEEM WORDT GESTART ---\n");

        // 1. Temel Verilerin Oluşturulması ve ArrayList Kullanımı
        Land syrie = new Land("Syrië", false);
        Gemeente denHaag = new Gemeente("Den Haag", 550000, 100);
        Gemeente amsterdam = new Gemeente("Amsterdam", 900000, 50);
        
        List<Gemeente> gemeentes = new ArrayList<>();
        gemeentes.add(denHaag);
        gemeentes.add(amsterdam);

        AZC azcDenHaag = new AZC("AZC Den Haag Centrum", "Kerkstraat", "1", "2514AB", denHaag);

        // 2. STRATEGY PATTERN TESTİ
        System.out.println("--- 1. STRATEGY PATTERN (Automatische Plaatsing) ---");
        PlaatsingsContext context = new PlaatsingsContext();
        
        context.setStrategie(new HoogsteVrijePlaatsenStrategie());
        Gemeente secilen1 = context.voerPlaatsingUit(gemeentes);
        System.out.println("Strategie 1 (Meeste vrije plaatsen): Gekozen Gemeente -> " + secilen1.getNaam());

        context.setStrategie(new LaagsteRelatieveBelastingStrategie());
        Gemeente secilen2 = context.voerPlaatsingUit(gemeentes);
        System.out.println("Strategie 2 (Laagste relatieve belasting): Gekozen Gemeente -> " + secilen2.getNaam() + "\n");

        // 3. TEMPLATE METHOD PATTERN TESTİ
        System.out.println("--- 2. TEMPLATE METHOD PATTERN (Standaard Actiestappen) ---");
        Vluchteling ahmed = new Vluchteling("Ahmed Al-Fahad", syrie);
        
        ActieVerwerker registratie = new VluchtelingRegistratieActie(ahmed);
        registratie.verwerkActie(); // Kayıt şablonunu çalıştırır

        ActieVerwerker plaatsing = new VluchtelingPlaatsingActie(ahmed, azcDenHaag);
        plaatsing.verwerkActie(); // Yerleştirme şablonunu çalıştırır

        // 4. OBSERVER PATTERN TESTİ
        System.out.println("--- 3. OBSERVER PATTERN (Automatisch Meldingssysteem) ---");
        Berichtenbox azcBerichtenbox = new Berichtenbox();
        ahmed.getDossier().voegObserverToe(azcBerichtenbox); // AZC mesaj kutusunu dinleyici olarak ekliyoruz

        System.out.println("Systeem: Dossier van de vluchteling wordt bijgewerkt (Uitspraak: geaccepteerd)...");
        ahmed.getDossier().setUitspraak("geaccepteerd"); // Bu metot tetiklendiğinde Berichtenbox otomatik mesaj alacak
        
        System.out.println("\n--- SYSTEEMTEST SUCCESVOL AFGEROND ---");
    }
}