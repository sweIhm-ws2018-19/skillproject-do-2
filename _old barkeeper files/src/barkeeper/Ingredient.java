package barkeeper;

/**
 * Class represents an ingredient of a drink.
 * 
 * @author Alexander Heinrizti
 *
 */
public class Ingredient {
    private final String name;
    private final String amount;

    /**
     * Constructor of an ingredient.
     * 
     * @param name
     *            name of the ingredient
     * @param amount
     *            the needed amount of the ingredient
     */
    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * Method returns the name of the ingredient.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method returns the needed amount of the ingredient.
     * 
     * @return amount
     */
    public String getAmount() {
        return amount;
    }
    
    @Override
    public String toString() {
        return "ingredient: " + name + "; amount: " + amount;
    }

}
