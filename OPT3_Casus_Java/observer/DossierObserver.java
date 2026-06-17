//Bildirim alacak her sınıfın (örneğin Berichtenbox) uyması gereken şablon.
package observer;

public interface DossierObserver {
    void update(Bericht bericht);
}