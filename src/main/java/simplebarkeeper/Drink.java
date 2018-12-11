package simplebarkeeper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which represents a drink.
 * @author Alexander Heinritzi
 */
public class Drink {
    private final String name;
    private final Flavour flavour;
    private final Daytime daytime;
    private final boolean containsAlcohol;
    private final String ingredients;
    private final List<String> steps;

    /**
     * Ctor of drink. Parameters as string for JsonCreator.
     * @param name            name of the drink
     * @param flavour         flavour of the drink as String
     * @param daytime         daytime of the drink as String
     * @param containsAlcohol true if the drink contains alcohol, false if not
     * @param ingredients     list of the ingredients of a drink
     */
    @JsonCreator
    public Drink(@JsonProperty("name") String name, @JsonProperty("flavour") String flavour,
        @JsonProperty("daytime") String daytime, @JsonProperty("containsAlcohol") String containsAlcohol,
        @JsonProperty("ingredients") String ingredients, @JsonProperty("steps") List<String> steps) {
        this.name = name;
        this.flavour = Flavour.valueOf(flavour);
        this.daytime = Daytime.valueOf(daytime);
        this.containsAlcohol = Boolean.parseBoolean(containsAlcohol);
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public Daytime getDaytime() {
        return daytime;
    }

    public boolean getContainsAlcohol() {
        return containsAlcohol;
    }

    public String getIngredients() {
        return ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("drinkName: ").append(name).append("; flavour: ").append(flavour).append("; daytime: ")
            .append(daytime).append("; containsAlcohol: ").append(containsAlcohol).append("\n")
            .append("# ingredients: ").append(ingredients).append("\n");

        int counter = 1;

        for (String step : steps) {
            sb.append("## step ").append(counter).append(": ").append(step).append("\n");
            counter++;
        }

        sb.append("\n");

        return sb.toString();
    }

}
