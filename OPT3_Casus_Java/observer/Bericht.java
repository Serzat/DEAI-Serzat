//İstenen gereksinimlere göre, AZC çalışanının göreceği mesajın içinde hangi mülteciyle ilgili olduğu ve mesajın türü gibi detaylar olmalı.

package observer;

import models.Vluchteling;

public class Bericht {
    private Vluchteling vluchteling;
    private String typeBericht;
    private String inhoud;
    private boolean isVerwerkt;

    public Bericht(Vluchteling vluchteling, String typeBericht, String inhoud) {
        this.vluchteling = vluchteling;
        this.typeBericht = typeBericht;
        this.inhoud = inhoud;
        this.isVerwerkt = false; // Başlangıçta işlenmedi olarak işaretlenir [cite: 176]
    }

    public String getInhoud() { return inhoud; }
    public Vluchteling getVluchteling() { return vluchteling; }
    public void markeerAlsVerwerkt() { this.isVerwerkt = true; }
}