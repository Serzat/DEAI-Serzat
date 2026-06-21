package models;
// Declares a public class named Land. This acts as a blueprint for country objects.
public class Land {
    // Private text variable to store the name of the country (e.g., "Syrië").
    private String naam;
    // Private boolean (true/false) variable. In the COA system, some countries 
    // are designated as "safe" (veilig), which affects the asylum procedure.
    private boolean isVeilig;
    // Constructor method: Called to create a new Land object. 
    // Takes the country's name and its safety status as parameters.
    public Land(String naam, boolean isVeilig) {
        this.naam = naam;
        this.isVeilig = isVeilig;
    }
    // Getter method: Allows outside classes to read the country's name.
    public String getNaam() { return naam; }
    // Getter method for booleans. Notice it's called 'isVeilig()' instead of 'getIsVeilig()', 
    // which is the standard naming convention in Java for boolean getters.
    public boolean isVeilig() { return isVeilig; }
}



//Amacımız: Sisteme, mültecinin geldiği ülkenin adını 
// ve güvenli olup olmadığını öğreten çok basit bir "Bilgi Kartı" hazırlamak.