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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static final String LOGGER_MESSAGE = "Something went wrong...";
    private static final String ALEXA_ANSWER_EMPFEHLUNG_BARKEEPER = "Dein Barkeeper empfiehlt dir: ";
    private static final String ALEXA_ANSWER_DRINK_AKTUELL_NICHT_BEKANNT = "Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt";
    private static final Logger LOGGER = Logger.getLogger(ListOfDrinks.class.getName());

    private final Map<String, Drink> drinks;
    private final Random random = new Random();

    /**
     * Ctor for ListOfDrinks with drinks from resources.
     */
    public ListOfDrinks() {
        drinks = getListFromJson("/initialDrinkList.json");
    }

    /**
     * Gets the the initialDrinkList from the resources.
     * 
     * @return The initialDrinkList from the repository. Empty list if file not
     *         present.
     */
    Map<String, Drink> getListFromJson(String path) {
        URL url = this.getClass().getResource(path);
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> initialDrinkList;

        try {
            initialDrinkList = om.readValue(file, mapType);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, LOGGER_MESSAGE, e);
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

        URL url = this.getClass().getResource("/");
        String pathWithoutPercents = url.getFile().replace("%20", " ");

        StringBuilder sb = new StringBuilder();
        File file = new File(sb.append(pathWithoutPercents).append("favourite.txt").toString());

        try (FileWriterWithEncoding fw = new FileWriterWithEncoding(file, StandardCharsets.UTF_8)) {
            fw.write(drinkName + "\r\n");
            fw.flush();
        } catch (IOException e) {
            LOGGER.log(Level.INFO, LOGGER_MESSAGE, e);
        }

        sb = new StringBuilder();

        return sb.append(drinkName).append(" wurde als dein neuer Lieblingsdrink gespeichert").toString();
    }

    /**
     * Getter for the favorite drink.
     * 
     * @return The recent favorite drink.
     */
    public String getFavorite() {
        URL url = this.getClass().getResource("/favourite.txt");

        if (url == null) {
            return "Tut mir leid, bisher wurde keine Favourit festgelegt";
        }

        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        StringBuilder sb = new StringBuilder();

        try (BufferedReader isr = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String drink = drinks.get(isr.readLine()).getName();
            sb.append("Dein Lieblingsdrink ist ").append(drink);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, LOGGER_MESSAGE, e);
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

    public String getRandomDrinkByFlavour(String flavour, String containsAlcohol, LocalTime time) {
        boolean isFlavourContained = false;

        for (Flavour value : Flavour.values()) {
            if (value.toString().equalsIgnoreCase(flavour)) {
                isFlavourContained = true;
            }

        }

        if (!isFlavourContained) {
            return "Dieser Geschmack ist mir leider nicht bekannt";
        }

        List<Drink> selectedDrinks = new ArrayList<>();

        for (Drink drink : drinks.values()) {
            if ((drink.getContainsAlcohol() == Boolean.parseBoolean(containsAlcohol))
                    && (drink.getFlavour() == Flavour.valueOf(flavour)) && drinkFitsDaytime(drink, time)) {
                selectedDrinks.add(drink);
            }
        }

        if (selectedDrinks.isEmpty()) {
            return ALEXA_ANSWER_DRINK_AKTUELL_NICHT_BEKANNT;
        }

        StringBuilder sb = new StringBuilder();

        return sb.append(ALEXA_ANSWER_EMPFEHLUNG_BARKEEPER)
                .append(selectedDrinks.get(random.nextInt(selectedDrinks.size())).getName()).toString();
    }

    public String getRandomDrinkByIngredient(String ingredient, String containsAlcohol, LocalTime time) {
        List<Drink> selectedDrinks = new ArrayList<>();

        for (Drink drink : drinks.values()) {
            if (drink.getIngredients() != null && (drink.getContainsAlcohol() == Boolean.parseBoolean(containsAlcohol))
                    && (drink.getIngredients().toLowerCase().contains(ingredient.toLowerCase()))
                    && drinkFitsDaytime(drink, time)) {
                selectedDrinks.add(drink);
            }
        }

        if (selectedDrinks.isEmpty()) {
            return ALEXA_ANSWER_DRINK_AKTUELL_NICHT_BEKANNT;
        }

        StringBuilder sb = new StringBuilder();

        return sb.append(ALEXA_ANSWER_EMPFEHLUNG_BARKEEPER)
                .append(selectedDrinks.get(random.nextInt(selectedDrinks.size())).getName()).toString();
    }

    public String getRandomDrinkByAlcohol(String containsAlcohol, LocalTime time) {
        List<Drink> selectedDrinks = new ArrayList<>();

        for (Drink drink : drinks.values()) {
            if ((drink.getContainsAlcohol() == Boolean.parseBoolean(containsAlcohol))
                    && drinkFitsDaytime(drink, time)) {
                selectedDrinks.add(drink);
            }
        }

        if (selectedDrinks.isEmpty()) {
            return ALEXA_ANSWER_DRINK_AKTUELL_NICHT_BEKANNT;
        }

        StringBuilder sb = new StringBuilder();

        return sb.append(ALEXA_ANSWER_EMPFEHLUNG_BARKEEPER)
                .append(selectedDrinks.get(random.nextInt(selectedDrinks.size())).getName()).toString();
    }

    boolean drinkFitsDaytime(Drink drink, LocalTime time) {
        Daytime drinkDayTime = drink.getDaytime();

        if (drinkDayTime.equals(Daytime.ALLDAY)) {
            return true;
        }

        LocalTime drinkStartTime = drinkDayTime.getStartTime();
        LocalTime drinkEndTime = drinkDayTime.getEndTime();

        if (drinkDayTime.equals(Daytime.EVENING)) {
            return (time.isAfter(drinkStartTime) || time.isBefore(drinkEndTime));
        }

        return time.isAfter(drinkStartTime) && time.isBefore(drinkEndTime);
    }

    int getSize() {
        return drinks.size();
    }

    Drink getDrink(String drinkName) {
        return drinks.get(drinkName);
    }

}
