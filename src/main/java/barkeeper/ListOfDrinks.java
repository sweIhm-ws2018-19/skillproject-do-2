package main.java.barkeeper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.impl.Log4JLogger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Class represents a list of drinks.
 * 
 * @author Felix Haala
 *
 */
public class ListOfDrinks {

    private final static Log4JLogger LOGGER = new Log4JLogger("Logger");

    private final Map<String, Drink> drinks;
    private Drink favorite;

    /**
     * Ctor for ListOfDrinks with preset drinks.
     * 
     * @param initializeList Imports an initial list of drinks if true.
     * @throws IOException Exception if any problems occur while reading the file.
     */

    /**
     * Ctor for ListOfDrinks with preset drinks.
     * 
     * @throws IOException
     */
    public ListOfDrinks() {
        drinks = getInitialListFromJson();
    }

    /**
     * Gets the the initialDrinkList from the resources.
     * 
     * @throws IOException Exception if any problems occur while reading the file.
     */
    public Map<String, Drink> getInitialListFromJson() {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> initialDrinkList = new HashMap<String, Drink>();

        try {
            initialDrinkList = om.readValue(file, mapType);
        } catch (IOException e) {
            LOGGER.fatal("initialDrinkList.json not found or wrong format. Empty list returned.", e);
        }

        return initialDrinkList;
    }

    /**
     * Gets a drink by it's name.
     * 
     * @param drinkName The name of the drink.
     * @return The requested drink.
     */
    public Drink getDrinkByName(String drinkName) {
        return drinks.get(drinkName);
    }

    /**
     * Gets the given drink if it exists and returns a string of a listing of
     * ingredients for Alexa. Or a string that tells the user that the the drink
     * doesn't exist.
     * 
     * @param drink
     * @return String for Alexa
     */
    public String listIngredients(String drink) {
        if (drinks.containsKey(drink)) {
            return drinks.get(drink).listIngredients();

        }
        return "Der von Ihnen genannt Drink ist mir leider nicht bekannt.";
    }

    /**
     * Setter for a favorite drink.
     * 
     * @param drink The drink which will be the new favorite.
     */
    public void setFavorite(Drink drink) {
        favorite = drink;
    }

    /**
     * Getter for the favorite drink.
     * 
     * @return The recent favorite drink.
     */
    public Drink getFavorite() {
        return favorite;
    }

}
