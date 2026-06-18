//Belediyenin adını, nüfusunu ve sunduğu sığınma yeri sayısını tutuyoruz. (Bunu bir sonraki aşamada Strategy Pattern ile en uygun belediyeyi seçerken kullanacağız).
package models;
public class Gemeente {
    private String naam;
    private int aantalInwoners;
    private int aangebodenPlaatsen;
    private int aantalGeplaatsteVluchtelingen; // Yeni eklendi

    public Gemeente(String naam, int aantalInwoners, int aangebodenPlaatsen) {
        this.naam = naam;
        this.aantalInwoners = aantalInwoners;
        this.aangebodenPlaatsen = aangebodenPlaatsen;
        this.aantalGeplaatsteVluchtelingen = 0;
    }

    // Boş yer sayısını hesaplar
    public int getVrijePlaatsen() {
        return aangebodenPlaatsen - aantalGeplaatsteVluchtelingen;
    }

    // Nüfusa göre yoğunluğu hesaplar (Düşük olması istenir)
    public double getRelatieveBelasting() {
        if (aantalInwoners == 0) return 0;
        return (double) aantalGeplaatsteVluchtelingen / aantalInwoners;
    }

    public void voegVluchtelingToe() {
        this.aantalGeplaatsteVluchtelingen++;
    }

    public String getNaam() { return naam; }
    public int getAantalInwoners() { return aantalInwoners; }
    public int getAangebodenPlaatsen() { return aangebodenPlaatsen; }
}
