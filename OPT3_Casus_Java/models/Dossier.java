//Mültecinin pasaport durumu, başvurusu ve dava süreçlerini takip ettiğimiz yer. (Observer Patterni kurgularken bu sınıfta bir değişiklik olduğunda bildirim göndereceğiz).
package models;
public class Dossier {
    private boolean paspoortGetoond;
    private boolean asielaanvraagCompleet;
    private boolean rechterToegewezen;
    private String uitspraak; // "geen", "geaccepteerd", "afgewezen"
    private boolean isTeruggekeerd;

    public Dossier() {
        this.paspoortGetoond = false;
        this.asielaanvraagCompleet = false;
        this.rechterToegewezen = false;
        this.uitspraak = "geen";
        this.isTeruggekeerd = false;
    }

    // Getters ve Setters
    public void setUitspraak(String uitspraak) { this.uitspraak = uitspraak; }
    public String getUitspraak() { return uitspraak; }
    // Diğer temel getter/setter metodlarını IDE'nin otomatik oluşturucu ile hızlıca ekleyebilirsin.
}
