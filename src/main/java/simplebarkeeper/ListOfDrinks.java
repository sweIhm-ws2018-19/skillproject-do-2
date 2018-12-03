package simplebarkeeper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Class represents a list of drinks.
 * @author Felix Haala
 */
public class ListOfDrinks {
    private final static String PATH = "/initialDrinkList.json";

    private final Map<String, Drink> drinks;
    private Drink favorite;

    /**
     * Ctor for ListOfDrinks with drinks from resources.
     */
    public ListOfDrinks() {
        drinks = getListFromJson();
    }

    public String removeDrink(String drinkName) {
        StringBuilder sb = new StringBuilder();

        if (!drinks.containsKey(drinkName)) {
            sb.append(drinkName).append(" ist leider nicht vorhanden");
        } else {
            drinks.remove(drinkName);
            sb.append(drinkName).append("wurde entfernt");
        }

        return sb.toString();
    }

    public String addAndReplaceDrink(String name, String flavor, String daytime, String containsAlcohol,
        List<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();

        Drink drink = new Drink(name, flavor, daytime, containsAlcohol, ingredients);
        drinks.put(name, drink);

        return sb.append(name).append("wurde gespeichert").toString();
    }

    }

    /**
     * Gets the the initialDrinkList from the resources.
     * @return The initialDrinkList from the repository. Empty list if file not
     *         present.
     */
    public Map<String, Drink> getListFromJson() {
        URL url = this.getClass().getResource(PATH);
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> initialDrinkList;

        try {
            initialDrinkList = om.readValue(file, mapType);
        } catch (IOException e) {
            e.printStackTrace();
            initialDrinkList = new HashMap<>();
        }

        return initialDrinkList;
    }

    /**
     * Saves the included map of drinks to a file in the resources folder.
     */
    public void saveListAsJson() {
        URL url = this.getClass().getResource(PATH);
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        ObjectMapper om = new ObjectMapper();

        try {
            om.writerWithDefaultPrettyPrinter().writeValue(file, drinks);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets a drink by it's name.
     * @param  drinkName The name of the drink.
     * @return           The requested drink.
     */
    public Drink getDrinkByName(String drinkName) {
        return drinks.get(drinkName);
    }

    /**
     * Gets the given drink if it exists and returns a string of a listing of
     * ingredients for Alexa. Or a string that tells the user that the the drink
     * doesn't exist.
     * @param  drink The ingredients of this drink will be listed.
     * @return       String for Alexa
     */
    public String listIngredients(String drink) {
        if (drinks.containsKey(drink)) {
            return drinks.get(drink).listIngredients();

        }
        return "Der von Ihnen genannte Drink ist mir leider nicht bekannt.";
    }

    /**
     * Setter for a favorite drink.
     * @param drink The drink which will be the new favorite.
     */
    public void setFavorite(Drink drink) {
        favorite = drink;
    }

    /**
     * Getter for the favorite drink.
     * @return The recent favorite drink.
     */
    public Drink getFavorite() {
        return favorite;
    }

    /**
     * Returns how many drinks are in this list.
     * @return Number of drinks in this list.
     */
    public int getSize() {
        return drinks.size();
    }

}
