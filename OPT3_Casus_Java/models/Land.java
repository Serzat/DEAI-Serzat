package models;

public class Land {
    private String naam;
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
