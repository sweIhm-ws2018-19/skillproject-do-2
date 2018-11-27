package simplebarkeeper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
     * @param name   name of the ingredient
     * @param amount the needed amount of the ingredient
     */
    @JsonCreator
    public Ingredient(@JsonProperty("name") String name, @JsonProperty("amount") String amount) {
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
        StringBuilder sb = new StringBuilder();
        sb.append("ingredient: ").append(name).append("; amount: ").append(amount);
        return sb.toString();
    }

}
