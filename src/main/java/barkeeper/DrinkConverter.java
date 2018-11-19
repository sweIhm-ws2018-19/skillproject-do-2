package main.java.barkeeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DrinkConverter {

    List<Drink> drinks = new ArrayList<>();

    public void convert() throws IOException {
        try (InputStreamReader isr = new InputStreamReader(
                new FileInputStream("src/main/resources/initialDrinkList.txt"), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)) {
            String tmp = br.readLine();

            while (tmp != null && tmp.equals("--Next--")) {
                String drinkName = br.readLine();
                String flavour = br.readLine();
                String daytime = br.readLine();
                boolean containsAlcohol;

                tmp = br.readLine();

                if (tmp.contains("true")) {
                    containsAlcohol = true;
                } else {
                    containsAlcohol = false;
                }

                List<Ingredient> ingredients = new ArrayList<>();

                tmp = br.readLine();

                if (tmp != null && tmp.equals("--Ingredients--")) {
                    tmp = br.readLine();
                    while (!tmp.equals("--Next--")) {
                        String name = tmp;
                        String amount = br.readLine();
                        Ingredient ingredient = new Ingredient(name, amount);
                        ingredients.add(ingredient);
                        tmp = br.readLine();
                    }
                }

                Drink drink = new Drink(drinkName, flavour, daytime, containsAlcohol, ingredients);
                drinks.add(drink);
            }
        }
    }

    public void toJSON() throws IOException {
        File file = new File("src/main/resources/initialDrinkList.json");
        ObjectMapper om = new ObjectMapper();

        for (Drink drink : drinks) {
            List<Ingredient> ingredients = drink.getIngredients();
            for (Ingredient ingredient : ingredients) {
                om.writeValue(file, ingredient);
            }
            om.writeValue(file, drink);
        }
    }

    @Override
    public String toString() {
        String tmp = "";
        for (Drink drink : drinks) {
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
        dc.toJSON();
//        System.out.println(dc.toString());
    }

}
