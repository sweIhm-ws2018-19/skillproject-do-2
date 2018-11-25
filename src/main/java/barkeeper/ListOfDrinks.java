package main.java.barkeeper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    private final Map<String, Drink> drinks;
    private Drink favorite;

    /**
     * Ctor for ListOfDrinks.
     * 
     * @param initializeList Imports an initial list of drinks if true.
     * @throws IOException Exception if any problems occur while reading the file.
     */
    public ListOfDrinks(boolean initializeListFromResources) throws IOException {
        if (initializeListFromResources) {
            drinks = getInitialListFromJson();
        }
    }

    /**
     * Gets the the initialDrinkList from the resources.
     * 
     * @throws IOException Exception if any problems occur while reading the file.
     */
    public Map<String, Drink> getInitialListFromJson() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> initialDrinkList = om.readValue(file, mapType);
        return initialDrinkList;
    }

    public Drink getDrinByName(String drinkName) {
        return drinks.get(drinkName);
    }

//    public List<String> getIngredientsOf(String drink) {
//        
//    }
//
//    public Drink getRandomDrink(Flavor flavor, boolean containsAlcohol) {
//
//    }
//    
//    public Drink getRandomDrink(Ingredient ingredient, boolean containsAlcohol) {
//        
//    }
//    
//    public Drink getRandomDrink(boolean containsAlcohol) {
//        
//    }
//    
//    public boolean addDrink(String name) {
//        
//    }
//    
//    public boolean addIngredient(String name, Ingredient ingredient) {
//        
//    }
//    
//    public void setFlavour(String name, Flavor flavor) {
//        
//    }
//    
//    public void setDaytime(String name, Daytime daytime) {
//        
//    }
//    
//    public String help() {
//        
//    }

}
