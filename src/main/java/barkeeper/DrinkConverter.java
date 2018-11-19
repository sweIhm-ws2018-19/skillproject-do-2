package main.java.barkeeper;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DrinkConverter {

    List<Drink> drinks = new ArrayList<>();

    public void convert() throws IOException {
        try (
            InputStreamReader isr = new InputStreamReader(
                DrinkConverter.class.getResourceAsStream("resources/initialDrinkList.txt"));
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
        try(FileWriter fw = new FileWriter("resources/initialDrinkListAsJSON.txt")){
            // todo
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
        System.out.println(dc.toString());
    }

}
