
package barkeeper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import simpleBarkeeper.Daytime;
import simpleBarkeeper.Drink;
import simpleBarkeeper.Flavor;
import simpleBarkeeper.Ingredient;

public class DrinkTest {

    @Test
    public void getNameTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);
        Assert.assertEquals("Test", drink.getName());
    }

    @Test
    public void getFlavorTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);
        Assert.assertEquals(Flavor.SWEET, drink.getFlavor());

    }

    @Test
    public void getDaytimeTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);
        Assert.assertEquals(Daytime.MORNING, drink.getDaytime());

    }

    @Test
    public void getContainsAlcoholTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);
        Assert.assertTrue(drink.getContainsAlcohol());
    }

    @Test
    public void getIngredientsTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient cola = new Ingredient("Cola", "100 Milliliter");
        ingredients.add(cola);
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);

        List<Ingredient> test = new ArrayList<>();
        test.add(cola);

        Assert.assertEquals(test, drink.getIngredients());
    }

    @Test
    public void listIngredientsTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient cola = new Ingredient("Cola", "100 Milliliter");
        Ingredient fanta = new Ingredient("Fanta", "200 Milliliter");
        Ingredient sprite = new Ingredient("Sprite", "300 Milliliter");
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);
        Assert.assertEquals("Für Test sind leider keine Zutaten gespeichert.", drink.listIngredients());
        ingredients.add(cola);
        Assert.assertEquals("Test enthält 100 Milliliter Cola", drink.listIngredients());
        ingredients.add(fanta);
        Assert.assertEquals("Test enthält folgende Zutaten: 100 Milliliter Cola und 200 Milliliter Fanta.",
                drink.listIngredients());
        ingredients.add(sprite);
        Assert.assertEquals(
                "Test enthält folgende Zutaten: 100 Milliliter Cola, 200 Milliliter Fanta und 300 Milliliter Sprite.",
                drink.listIngredients());
    }

    @Test
    public void toStringTest() {
        List<Ingredient> ingredients = new ArrayList<>();
        Flavor flavor = Flavor.SWEET;
        Daytime daytime = Daytime.MORNING;
        Drink drink = new Drink("Test", flavor, daytime, true, ingredients);
        Assert.assertEquals("drinkName: Test; falvor: süß; daytime: morning; containsAlcohol: true", drink.toString());
    }
    
    
    @Test
    public void testStingConstructor() {
        List<Ingredient> ingredients = new ArrayList<>();
        Drink drink = new Drink("Test", "sweet", "morning", "true", ingredients);
        Assert.assertEquals(Flavor.SWEET, drink.getFlavor());
        Assert.assertEquals(Daytime.MORNING, drink.getDaytime());
        Assert.assertTrue(drink.getContainsAlcohol());
        drink = new Drink("Test", "sour", "noon", "false", ingredients);
        Assert.assertEquals(Flavor.SOUR, drink.getFlavor());
        Assert.assertEquals(Daytime.NOON, drink.getDaytime());
        Assert.assertFalse(drink.getContainsAlcohol());
        drink = new Drink("Test", "BiTtER", "EveNing", "false", ingredients);
        Assert.assertEquals(Flavor.BITTER, drink.getFlavor());
        Assert.assertEquals(Daytime.EVENING, drink.getDaytime());
        Assert.assertFalse(drink.getContainsAlcohol());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFindFlavor() {
        List<Ingredient> ingredients = new ArrayList<>();
        Drink drink = new Drink("Test", "flavor", "morning", "true", ingredients);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFindDaytime() {
        List<Ingredient> ingredients = new ArrayList<>();
        Drink drink = new Drink("Test", "sweet", "daytime", "true", ingredients);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFindContainsAlcohol() {
        List<Ingredient> ingredients = new ArrayList<>();
        Drink drink = new Drink("Test", "sweet", "morning", "containsAlcohol", ingredients);
    
    }
}
