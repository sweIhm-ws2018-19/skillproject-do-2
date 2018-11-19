package main.java.barkeeper;

import java.util.List;

public class Drink {
    private final String name;
    private final Flavor flavor;
    private final Daytime daytime;
    private final boolean containsAlcohol;
    private final List<Ingredient> ingredients;

    /**
     * Constructor of drink.
     * 
     * @param name
     *            name of the drink
     * @param flavor
     *            flavor of the drink as String
     * @param daytime
     *            daytime of the drink as String
     * @param containsAlcohol
     *            true if the drink contains alcohol, false if not
     * @param ingredient
     *            list of the ingredients of a drink
     */
    public Drink(String name, String flavor, String daytime, boolean containsAlcohol,
            List<Ingredient> ingredient) {
        this.name = name;
        this.flavor = findFlavor(flavor);
        this.daytime = findDaytime(daytime);
        this.containsAlcohol = containsAlcohol;
        this.ingredients = ingredient;
    }

    /**
     * Method gets a flavor as string and converts it to Flavor(enum).
     * 
     * @param flavor
     *            as string
     * @return flavor as enum.
     */
    private Flavor findFlavor(String flavor) {
        Flavor tmp;
        switch (flavor) {
        case "sweet":
            tmp = Flavor.SWEET;
            break;
        case "sour":
            tmp = Flavor.SOUR;
            break;
        case "bitter":
            tmp = Flavor.BITTER;
            break;
        default:
            throw new IllegalArgumentException("\"" + flavor + "\""
                    + "is not a valid flavour. Valid flavour are \"sweet\", \"sour\" or \"bitter\"");
        }
        return tmp;
    }

    /**
     * Method gets a daytime as string and converts it to Daytime(enum).
     * 
     * @param daytime
     *            as string
     * @return daytime as enum.
     */
    private Daytime findDaytime(String daytime) {
        Daytime tmp;
        switch (daytime) {
        case "morning":
            tmp = Daytime.MORNING;
            break;
        case "noon":
            tmp = Daytime.NOON;
            break;
        case "evening":
            tmp = Daytime.EVENING;
            break;
        default:
            throw new IllegalArgumentException("\"" + daytime + "\""
                    + "is not a valid daytime. Valid daytimes are \"morning\", \"noon\" or \"evening\"");
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
    public boolean containsAlcohol() {
        return containsAlcohol;
    }

    /**
     * @return the ingredient
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public String listIngredients() {
        if (ingredients.size() == 0) {
            return "Fuer" + name + "sind leider keine Zutaten gespeichert.";
        }
        if(ingredients.size() == 1) {
            return name + "enth�lt" + ingredients.get(0).getAmount() + ingredients.get(0).getName();
        }
        
        String listOfIngredients = name + "enthaelt folgende Zutaten: ";
        for (int i = 0; i < ingredients.size() - 1; i++) {
            listOfIngredients += ingredients.get(i).getAmount() + ingredients.get(i).getName() + ",";
        }
        listOfIngredients += "und " + ingredients.get(ingredients.size() - 1) + ".";
        return listOfIngredients;
    }
    
    @Override
    public String toString() {
        return "drinkName: " + name + "; falvor: " + flavor.getFlavor() + "; daytime: " + daytime.getDaytime() + "; containsAlcohol: " + containsAlcohol;
    }
    
}
