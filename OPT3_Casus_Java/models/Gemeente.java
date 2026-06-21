package models;
public class Gemeente {
    private String naam;
    private int aantalInwoners;
    private int aangebodenPlaatsen;
    private int aantalGeplaatsteVluchtelingen; 

    //CONSTRUCTOR GEMEENTE 
    public Gemeente(String naam, int aantalInwoners, int aangebodenPlaatsen) {
        this.naam = naam;
        this.aantalInwoners = aantalInwoners;
        this.aangebodenPlaatsen = aangebodenPlaatsen;
        this.aantalGeplaatsteVluchtelingen = 0;
    }

    // Public method that returns an integer (int). Calculates and returns the remaining free places.
    public int getVrijePlaatsen() {
        return aangebodenPlaatsen - aantalGeplaatsteVluchtelingen;
    }

    // Public method that returns a decimal number (double). Calculates population density.
    public double getRelatieveBelasting() {
        //If population is 0, return 0 to prevent a "divide by zero" math error.
        if (aantalInwoners == 0) return 0;
        // Divides placed refugees by total population. (double) forces a precise decimal calculation.
        return (double) aantalGeplaatsteVluchtelingen / aantalInwoners;
    }
    // Public method that returns nothing (void). Used to update the system when a refugee is placed.
    public void voegVluchtelingToe() {
        // Increases the 'aantalGeplaatsteVluchtelingen' variable by exactly 1 (the ++ operator).
        this.aantalGeplaatsteVluchtelingen++;
    }
    // Getter method: Allows outside classes to read the private naam,aantalInwoners and aangebodenPlaatsen variable.
    public String getNaam() { return naam; }
    public int getAantalInwoners() { return aantalInwoners; }
    public int getAangebodenPlaatsen() { return aangebodenPlaatsen; }
}


//boş yer var mı, yoğunluk ne durumda) tamamen bu sınıfın sorumluluğundadır. 
// İleride Strategy Pattern karar verirken hep bu dosyaya bakıp "Sende yer var mı?" diye soracaktır.
