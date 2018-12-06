package simplebarkeeper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which represents a drink.
 * 
 * @author Alexander Heinritzi
 *
 */
public class Drink {
    private final String name;
    private final Flavor flavor;
    private final Daytime daytime;
    private final boolean containsAlcohol;
    private final String ingredients;
    private final List<String> steps;

    /**
     * Ctor of drink. Parameters as string for JsonCreator.
     * 
     * @param name            name of the drink
     * @param flavor          flavor of the drink as String
     * @param daytime         daytime of the drink as String
     * @param containsAlcohol true if the drink contains alcohol, false if not
     * @param ingredients     list of the ingredients of a drink
     */
    @JsonCreator
    public Drink(@JsonProperty("name") String name, @JsonProperty("flavor") String flavor,
            @JsonProperty("daytime") String daytime, @JsonProperty("containsAlcohol") String containsAlcohol,
            @JsonProperty("ingredients") String ingredients, @JsonProperty("steps") List<String> steps) {
        this.name = name;
        this.flavor = findFlavor(flavor);
        this.daytime = findDaytime(daytime);
        this.containsAlcohol = findContainsAlcohol(containsAlcohol);
        this.ingredients = ingredients;
    }

//    /**
//     * Ctor of drink.
//     * 
//     * @param name            name of the drink
//     * @param flavor          flavor of the drink as String
//     * @param daytime         daytime of the drink as String
//     * @param containsAlcohol true if the drink contains alcohol, false if not
//     * @param ingredients     list of the ingredients of a drink
//     */
//    public Drink(String name, Flavor flavor, Daytime daytime, boolean containsAlcohol, List<Ingredient> ingredients) {
//        this.name = name;
//        this.flavor = flavor;
//        this.daytime = daytime;
//        this.containsAlcohol = containsAlcohol;
//        this.ingredients = ingredients;
//    }

//    /**
//     * Method gets information if drink contains alcohol as string and converts it
//     * to boolean.
//     * 
//     * @param containsAlcohol True if drink contains alcohol.
//     * @return Boolean representation of the string true/false.
//     */
//    private boolean findContainsAlcohol(String containsAlcohol) {
//        boolean tmp;
//        switch (containsAlcohol.toLowerCase()) {
//        case "true":
//            tmp = true;
//            break;
//        case "false":
//            tmp = false;
//            break;
//        default:
//            StringBuilder sb = new StringBuilder();
//            sb.append("\"").append(containsAlcohol).append("\"")
//                    .append("is not a valid boolean. Valid booleans are \"false\" or \"true\"");
//            throw new IllegalArgumentException(sb.toString());
//        }
//        return tmp;
//    }
//
//    /**
//     * Method gets a flavor as string and converts it to Flavor(enum).
//     * 
//     * @param flavor as string
//     * @return flavor as enum.
//     */
//    private Flavor findFlavor(String flavor) {
//        Flavor tmp;
//        switch (flavor.toUpperCase()) {
//        case "SWEET":
//            tmp = Flavor.SWEET;
//            break;
//        case "SOUR":
//            tmp = Flavor.SOUR;
//            break;
//        case "BITTER":
//            tmp = Flavor.BITTER;
//            break;
//        default:
//            StringBuilder sb = new StringBuilder();
//            sb.append("\"").append(flavor).append("\"")
//                    .append("is not a valid flavour. Valid flavour are \"SWEET\", \"SOUR\" or \"BITTER\"");
//            throw new IllegalArgumentException(sb.toString());
//        }
//        return tmp;
//    }
//
//    /**
//     * Method gets a daytime as string and converts it to Daytime(enum).
//     * 
//     * @param daytime as string
//     * @return daytime as enum.
//     */
//    private Daytime findDaytime(String daytime) {
//        Daytime tmp;
//        switch (daytime.toUpperCase()) {
//        case "MORNING":
//            tmp = Daytime.MORNING;
//            break;
//        case "NOON":
//            tmp = Daytime.NOON;
//            break;
//        case "EVENING":
//            tmp = Daytime.EVENING;
//            break;
//        default:
//            StringBuilder sb = new StringBuilder();
//            sb.append("\"").append(daytime).append("\"")
//                    .append("is not a valid daytime. Valid daytimes are \"MORNING\", \"NOON\" or \"EVENING\"");
//            throw new IllegalArgumentException(sb.toString());
//        }
//        return tmp;
//    }
//
//    /**
//     * @return the name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * @return the flavor
//     */
//    public Flavor getFlavor() {
//        return flavor;
//    }
//
//    /**
//     * @return the daytime
//     */
//    public Daytime getDaytime() {
//        return daytime;
//    }
//
//    /**
//     * @return the containsAlcohol
//     */
//    public boolean getContainsAlcohol() {
//        return containsAlcohol;
//    }
//
////    /**
////     * @return the ingredient
////     */
////    public List<Ingredient> getIngredients() {
////        return ingredients;
////    }
//
//    /**
//     * Gets a string representation for Alexa of the ingredients of a drink.
//     * 
//     * @return The string for Alexa with the ingredients of a drink.
//     */
//    public String listIngredients() {
//        StringBuilder sb = new StringBuilder();
//
//        if (ingredients.isEmpty()) {
//            sb.append("Für ").append(name).append(" sind leider keine Zutaten gespeichert.");
//            return sb.toString();
//        }
//        if (ingredients.size() == 1) {
//            sb.append(name).append(" enthält ").append(ingredients.get(0).getAmount()).append(" ")
//                    .append(ingredients.get(0).getName());
//            return sb.toString();
//        }
//        sb.append(name).append(" enthält folgende Zutaten: ");
//
//        for (int i = 0; i < ingredients.size() - 2; i++) {
//            sb.append(ingredients.get(i).getAmount()).append(" ").append(ingredients.get(i).getName()).append(", ");
//        }
//
//        sb.append(ingredients.get(ingredients.size() - 2).getAmount()).append(" ")
//                .append(ingredients.get(ingredients.size() - 2).getName()).append(" und ")
//                .append(ingredients.get(ingredients.size() - 1).getAmount()).append(" ")
//                .append(ingredients.get(ingredients.size() - 1).getName()).append(".");
//
//        return sb.toString();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("drinkName: ").append(name).append("; falvor: ").append(flavor.getFlavorName()).append("; daytime: ")
//                .append(daytime.getDaytimeName()).append("; containsAlcohol: ").append(containsAlcohol);
//        return sb.toString();
//    }
}
