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

public class DrinkConverter {

    Map<String, Drink> drinks = new HashMap<>();

    public void convert() throws IOException {
        try (
            InputStreamReader isr = new InputStreamReader(
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

    public void toJSON() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();

        om.writerWithDefaultPrettyPrinter().writeValue(file, drinks);
    }

    public void fromJSON() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();
        TypeFactory typeFactory = om.getTypeFactory();
        MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, Drink.class);

        Map<String, Drink> drinksTemp = om.readValue(file, mapType);
        System.out.println(drinksTemp.toString());

    }

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

    public static void main(String[] args) throws IOException {
        DrinkConverter dc = new DrinkConverter();
        dc.convert();
//        System.out.println(dc.toString());
        dc.toJSON();
        dc.fromJSON();
    }

}
