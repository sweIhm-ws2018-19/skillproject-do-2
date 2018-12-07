package simplebarkeeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.output.FileWriterWithEncoding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Class represents a list of drinks.
 * 
 * @author Felix Haala
 */
public class ListOfDrinks {
    private final static String JSON_PATH = "/initialDrinkList.json";
    private final static String FAVOURITE_PATH = "/favourite.txt";

    private final Map<String, Drink> drinks;

    /**
     * Ctor for ListOfDrinks with drinks from resources.
     */
    public ListOfDrinks() {
        drinks = getListFromJson();
    }

    /**
     * Gets the the initialDrinkList from the resources.
     * 
     * @return The initialDrinkList from the repository. Empty list if file not
     *         present.
     */
    public Map<String, Drink> getListFromJson() {
        URL url = this.getClass().getResource(JSON_PATH);
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
     * Gets a drink by it's name.
     * 
     * @param drinkName The name of the drink.
     * @return The requested drink.
     */
    public Drink get(String drinkName) {
        return drinks.get(drinkName);
    }

    /**
     * Setter for a favorite drink.
     * 
     * @param drink The drink which will be the new favorite.
     */
    public void setFavorite(Drink drink) {
        URL url = this.getClass().getResource(FAVOURITE_PATH);
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        try (FileWriterWithEncoding fw = new FileWriterWithEncoding(file, StandardCharsets.UTF_8)) {
            fw.write(drink.getName() + "\r\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the favorite drink.
     * 
     * @return The recent favorite drink.
     */
    public String getFavorite() {
        URL url = this.getClass().getResource(FAVOURITE_PATH);
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        if (!file.exists()) {
            return "Tut mir leid, bisher wurde keine Favourit festgelegt";
        }

        StringBuilder sb = new StringBuilder();

        try (BufferedReader isr = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String drink = drinks.get(isr.readLine()).getName();
            sb.append("Dein Lieblingsdrink heißt ").append(drink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * Returns how many drinks are in this list.
     * 
     * @return Number of drinks in this list.
     */
    public int getSize() {
        return drinks.size();
    }

}
