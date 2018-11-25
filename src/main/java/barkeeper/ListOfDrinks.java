package main.java.barkeeper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ListOfDrinks {

    private final Map<String, Drink> drinks = new HashMap<>();
    private Drink favourite;

    public void getInitialListfromJSON() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> drinksTemp = om.readValue(file, mapType);
        System.out.println(drinksTemp.toString());

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
