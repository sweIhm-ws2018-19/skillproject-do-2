package main.java.barkeeper;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Drink {
    private final String name;
    private final Flavor flavor;
    private final Daytime daytime;
    private final boolean containsAlcohol;
    private final List<Ingredient> ingredients;

    /**
     * Constructor of drink.
     * 
     * @param name            name of the drink
     * @param flavor          flavor of the drink as String
     * @param daytime         daytime of the drink as String
     * @param containsAlcohol true if the drink contains alcohol, false if not
     * @param ingredient      list of the ingredients of a drink
     */
    @JsonCreator
    public Drink(@JsonProperty("name") String name, @JsonProperty("flavor") String flavor,
            @JsonProperty("daytime") String daytime, @JsonProperty("containsAlcohol") String containsAlcohol,
            @JsonProperty("ingredients") List<Ingredient> ingredients) {
        this.name = name;
        this.flavor = findFlavor(flavor);
        this.daytime = findDaytime(daytime);
        this.containsAlcohol = findContainsAlcohol(containsAlcohol);
        this.ingredients = ingredients;
    }

    public Drink(String name, Flavor flavor, Daytime daytime, boolean containsAlcohol, List<Ingredient> ingredients) {
        this.name = name;
        this.flavor = flavor;
        this.daytime = daytime;
        this.containsAlcohol = containsAlcohol;
        this.ingredients = ingredients;
    }

    /**
     * Method gets information if drink contains alcohol as string and converts it
     * to boolean.
     * 
     * @param flavor as string
     * @return flavor as enum.
     */
    private boolean findContainsAlcohol(String containsAlcohol) {
        boolean tmp;
        switch (containsAlcohol.toLowerCase()) {
        case "true":
            tmp = true;
            break;
        case "false":
            tmp = false;
            break;
        default:
            throw new IllegalArgumentException(
                    "\"" + containsAlcohol + "\"" + "is not a valid boolean. Valid booleans are \"false\" or \"true\"");
        }
        return tmp;
    }

    /**
     * Method gets a flavor as string and converts it to Flavor(enum).
     * 
     * @param flavor as string
     * @return flavor as enum.
     */
    private Flavor findFlavor(String flavor) {
        Flavor tmp;
        switch (flavor.toUpperCase()) {
        case "SWEET":
            tmp = Flavor.SWEET;
            break;
        case "SOUR":
            tmp = Flavor.SOUR;
            break;
        case "BITTER":
            tmp = Flavor.BITTER;
            break;
        default:
            throw new IllegalArgumentException("\"" + flavor + "\""
                    + "is not a valid flavour. Valid flavour are \"SWEET\", \"SOUR\" or \"BITTER\"");
        }
        return tmp;
    }

    /**
     * Method gets a daytime as string and converts it to Daytime(enum).
     * 
     * @param daytime as string
     * @return daytime as enum.
     */
    private Daytime findDaytime(String daytime) {
        Daytime tmp;
        switch (daytime.toUpperCase()) {
        case "MORNING":
            tmp = Daytime.MORNING;
            break;
        case "NOON":
            tmp = Daytime.NOON;
            break;
        case "EVENING":
            tmp = Daytime.EVENING;
            break;
        default:
            throw new IllegalArgumentException("\"" + daytime + "\""
                    + "is not a valid daytime. Valid daytimes are \"MORNING\", \"NOON\" or \"EVENING\"");
        }
        return tmp;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the flavor
     */
    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * @return the daytime
     */
    public Daytime getDaytime() {
        return daytime;
    }

    /**
     * @return the containsAlcohol
     */
    public boolean getContainsAlcohol() {
        return containsAlcohol;
    }

    /**
     * @return the ingredient
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String listIngredients() {
        if (ingredients.isEmpty()) {
            return "Fuer " + name + " sind leider keine Zutaten gespeichert.";
        }
        if (ingredients.size() == 1) {
            return name + " enthaelt " + ingredients.get(0).getAmount() + " " + ingredients.get(0).getName();
        }
        String listOfIngredients = name + " enthält folgende Zutaten: ";
        for (int i = 0; i < ingredients.size() - 2; i++) {
            listOfIngredients += ingredients.get(i).getAmount() + " " + ingredients.get(i).getName() + ", ";
        }
        listOfIngredients += ingredients.get(ingredients.size() - 2).getAmount() + " "
                + ingredients.get(ingredients.size() - 2).getName() + " und "
                + ingredients.get(ingredients.size() - 1).getAmount() + " "
                + ingredients.get(ingredients.size() - 1).getName() + ".";

        return listOfIngredients;
    }

    @Override
    public String toString() {
        return "drinkName: " + name + "; falvor: " + flavor.getFlavorName() + "; daytime: " + daytime.getDaytimeName()
                + "; containsAlcohol: " + containsAlcohol;
    }
}
