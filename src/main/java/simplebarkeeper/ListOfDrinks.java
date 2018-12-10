package simplebarkeeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    private Map<String, Drink> getListFromJson() {
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
     * Setter for a favourite drink.
     * 
     * @param drinkName The drink which will be the new favourite.
     * @return The answer for alexa.
     */
    public String setFavorite(String drinkName) {
        drinkName = drinkName.toLowerCase();

        if (!drinks.containsKey(drinkName)) {
            return "Dieser Drink ist mir leider nicht bekannt";
        }

        URL url = this.getClass().getResource(FAVOURITE_PATH);
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        try (FileWriterWithEncoding fw = new FileWriterWithEncoding(file, StandardCharsets.UTF_8)) {
            fw.write(drinkName + "\r\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        return sb.append(drinkName).append(" wurde als dein neuer Lieblingsdrink gespeichert").toString();
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
            sb.append("Dein Lieblingsdrink ist ").append(drink);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getIngredients(String drinkName) {
        drinkName = drinkName.toLowerCase();

        if (!drinks.containsKey(drinkName)) {
            return "Dieser Drink ist mir leider nicht bekannt";
        }

        if (drinks.get(drinkName).getIngredients().isEmpty()) {
            return "Dieser Drink enthält keine weiteren Zutaten";
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(drinkName).append(" enthält folgende Zutaten: ").append(drinks.get(drinkName).getIngredients())
                .toString();
    }

    public String getRandomDrinkByFlavour(String flavour, String containsAlcohol) {
        if (Flavour.valueOf(flavour) == null) {
            return "Dieser Geschmack ist mir leider nicht bekannt";
        }

        List<Drink> selectedDrinks = new ArrayList<>();
        Random random = new Random();

        for (Drink drink : drinks.values()) {
            if ((drink.getContainsAlcohol() == Boolean.parseBoolean(containsAlcohol))
                    && (drink.getFlavour() == Flavour.valueOf(flavour)) && drinkFitsDaytime(drink)) {
                selectedDrinks.add(drink);
            }
        }

        if (selectedDrinks.size() == 0) {
            return "Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt";
        }

        StringBuilder sb = new StringBuilder();

        return sb.append("Dein Barkeeper empfiehlt dir: ")
                .append(selectedDrinks.get(random.nextInt(selectedDrinks.size()))).toString();
    }

    public String getRandomDrinkByIngredient(String ingredient, String containsAlcohol) {
        List<Drink> selectedDrinks = new ArrayList<>();
        Random random = new Random();

        for (Drink drink : drinks.values()) {
            if ((drink.getContainsAlcohol() == Boolean.parseBoolean(containsAlcohol))
                    && (drink.getIngredients().contains(ingredient)) && drinkFitsDaytime(drink)) {
                selectedDrinks.add(drink);
            }
        }

        if (selectedDrinks.size() == 0) {
            return "Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt";
        }

        StringBuilder sb = new StringBuilder();

        return sb.append("Dein Barkeeper empfiehlt dir: ")
                .append(selectedDrinks.get(random.nextInt(selectedDrinks.size()))).toString();
    }

    public String getRandomDrinkByAlcohol(String containsAlcohol) {
        List<Drink> selectedDrinks = new ArrayList<>();
        Random random = new Random();

        for (Drink drink : drinks.values()) {
            if ((drink.getContainsAlcohol() == Boolean.parseBoolean(containsAlcohol)) && drinkFitsDaytime(drink)) {
                selectedDrinks.add(drink);
            }
        }

        if (selectedDrinks.size() == 0) {
            return "Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt";
        }

        StringBuilder sb = new StringBuilder();

        return sb.append("Dein Barkeeper empfiehlt dir: ")
                .append(selectedDrinks.get(random.nextInt(selectedDrinks.size()))).toString();
    }

    private boolean drinkFitsDaytime(Drink drink) {
        LocalTime drinkStartTime = drink.getDaytime().getStartTime();

        if (drinkStartTime.equals(LocalTime.parse("00:00"))) {
            return true;
        }

        LocalTime now = LocalTime.now();
        LocalTime drinkEndTime = drink.getDaytime().getEndTime();

        if (now.isAfter(drinkStartTime) && now.isBefore(drinkEndTime)) {
            return true;
        }

        return false;
    }

    public int getSize() {
        return drinks.size();
    }

    public Drink getDrink(String drinkName) {
        return drinks.get(drinkName);
    }

}
