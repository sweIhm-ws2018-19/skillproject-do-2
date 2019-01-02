package simplebarkeeper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	 * Method that checks if the ListOfDrinks contains a given drink.
	 * 
	 * @param drinkName
	 *            The name of the drink.
	 * @return True if it contains the Drink, false if not.
	 */
	public boolean containsDrink(String drinkName) {
		return drinks.containsKey(drinkName);
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
