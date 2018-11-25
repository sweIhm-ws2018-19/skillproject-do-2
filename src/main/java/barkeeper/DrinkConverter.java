package main.java.barkeeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Class represents a converter for drinks. It is not part of the final product.
 * This tool is just a helper to produce an initial map of drinks. It reads a
 * text file and knows the tags "--Next--" and "--Ingredients--". If those words
 * occur in the text file, a new drink or a list of ingredients will be
 * generated. A text file will contain only one information per line.
 * 
 * @author Felix Haala
 *
 */
public class DrinkConverter {

    Map<String, Drink> drinks = new HashMap<>();

    /**
     * Converts the initialDrinkList.txt from the resources to a Map<String, Drink>.
     * 
     * @throws IOException Exception if any problems occur while reading the file.
     */
    public void convert() throws IOException {
        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/main/resources/initialDrinkList.txt"), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            String tmp = br.readLine();

            while (tmp != null && tmp.equals("--Next--")) {
                String drinkName = br.readLine();
                String flavour = br.readLine();
                String daytime = br.readLine();
                String containsAlcohol = br.readLine();

                List<Ingredient> ingredients = new ArrayList<>();

                tmp = br.readLine();

                if (tmp != null && tmp.equals("--Ingredients--")) {
                    tmp = br.readLine();
                    while (tmp != null && !tmp.equals("--Next--")) {
                        String name = tmp;
                        String amount = br.readLine();
                        Ingredient ingredient = new Ingredient(name, amount);
                        ingredients.add(ingredient);
                        tmp = br.readLine();
                    }
                }

                Drink drink = new Drink(drinkName, flavour, daytime, containsAlcohol, ingredients);
                drinks.put(drinkName, drink);
            }
        }
    }

    /**
     * Converts the included map of drinks to a initialDrinkList.json file in the
     * resources folder.
     * 
     * @throws IOException Exception if any problems occur while writing the file.
     */
    public void toJSON() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();

        om.writerWithDefaultPrettyPrinter().writeValue(file, drinks);
    }

    /**
     * Imports the the initialDrinkList file from the resources.
     * 
     * @throws IOException Exception if any problems occur while reading the file.
     */
    public void fromJSON() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> drinksTemp = om.readValue(file, mapType);
        System.out.println(drinksTemp.toString());

    }

    /**
     * Generates a string representation of the drinks in the classes map of drinks.
     */
    @Override
    public String toString() {
        String tmp = "";
        for (Drink drink : drinks.values()) {
            tmp += drink.toString() + "\n";
            for (Ingredient ingredient : drink.getIngredients()) {
                tmp += ingredient.toString() + " # ";
            }
            tmp = tmp.substring(0, tmp.length() - 3);
            tmp += "\n\n";
        }
        return tmp;
    }

//    public static void main(String[] args) throws IOException {
//        DrinkConverter dc = new DrinkConverter();
//        dc.convert();
////        System.out.println(dc.toString());
//        dc.toJSON();
////        dc.fromJSON();
//    }

}
