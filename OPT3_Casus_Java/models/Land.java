//Bir ülkenin adını ve güvenli ülke sayılıp sayılmadığını tutuyoruz
package models;
public class Land {
    private String naam;
    private boolean isVeilig;

    public Land(String naam, boolean isVeilig) {
        this.naam = naam;
        this.isVeilig = isVeilig;
    }

    public String getNaam() { return naam; }
    public boolean isVeilig() { return isVeilig; }
}