package models;

import java.util.List;
import java.util.Objects;
import service.RapportageService;

import static util.Validatie.vereisTekst;

public final class Beheerder {
    private final String naam;
    private final RapportageService rapportageService;

    public Beheerder(String naam) {
        this(naam, new RapportageService());
    }

    public Beheerder(String naam, RapportageService rapportageService) {
        this.naam = vereisTekst(naam, "naam");
        this.rapportageService = Objects.requireNonNull(
                rapportageService, "rapportageService mag niet null zijn.");
    }

    public void vraagRapportageOp(List<Gemeente> gemeentes) {
        System.out.println("Systeem: Beheerder " + naam + " heeft een rapportage aangevraagd.");
        rapportageService.genereerManagementRapport(gemeentes);
    }

}
