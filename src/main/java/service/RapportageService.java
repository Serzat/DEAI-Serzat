package service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import models.AZC;
import models.Gemeente;

public final class RapportageService {
    public void genereerManagementRapport(List<Gemeente> gemeentes) {
        Objects.requireNonNull(gemeentes, "gemeentes mag niet null zijn.");

        System.out.println("\n========== COA MANAGEMENTRAPPORT ==========");
        if (gemeentes.isEmpty()) {
            System.out.println("Geen gemeentelijke gegevens beschikbaar.");
            return;
        }

        for (Gemeente gemeente : gemeentes) {
            if (gemeente == null) {
                continue;
            }

            System.out.println("\nGemeente: " + gemeente.getNaam());
            System.out.println("Inwoners: " + formatteerGetal(gemeente.getAantalInwoners()));
            System.out.println("Aangeboden plaatsen: " + gemeente.getAangebodenPlaatsen());
            System.out.println("Geplaatste vluchtelingen: "
                    + gemeente.getAantalGeplaatsteVluchtelingen());
            System.out.println("Vrije plaatsen: " + gemeente.getVrijePlaatsen());
            System.out.printf(Locale.ROOT, "Relatieve belasting: %.4f per 1.000 inwoners%n",
                    gemeente.getRelatieveBelasting() * 1_000);
            System.out.println("AZC's:");

            if (gemeente.getAzcs().isEmpty()) {
                System.out.println("  - Geen actief AZC geregistreerd.");
                continue;
            }

            for (AZC azc : gemeente.getAzcs()) {
                System.out.println("  - " + azc.getNaam() + ", " + azc.getStraat() + " "
                        + azc.getHuisnummer() + ", " + azc.getPostcode()
                        + " | bewoners: " + azc.getGehuisvesteVluchtelingen().size());
            }
        }
        System.out.println("\n===========================================");
    }

    private static String formatteerGetal(int getal) {
        return String.format(Locale.forLanguageTag("nl-NL"), "%,d", getal);
    }
}
