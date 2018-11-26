package test.java.barkeeper;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import main.java.barkeeper.Daytime;
import main.java.barkeeper.Drink;
import main.java.barkeeper.Flavor;
import main.java.barkeeper.ListOfDrinks;

public class ListOfDrinksTest {

    @Test
    public void getDrinkByNameTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("Rote Schorle", drinkList.getDrinkByName("Rote Schorle").getName());
        Assert.assertEquals(Flavor.SWEET, drinkList.getDrinkByName("Rote Schorle").getFlavor());
        Assert.assertEquals(Daytime.NOON, drinkList.getDrinkByName("Rote Schorle").getDaytime());
        Assert.assertFalse(drinkList.getDrinkByName("Rote Schorle").getContainsAlcohol());
        Assert.assertTrue(drinkList.getDrinkByName("Rote Schorle").getIngredients().isEmpty());
    }

    @Test
    public void getFavoriteTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Drink drink = drinkList.getDrinkByName("Cola");
        drinkList.setFavorite(drink);
        Assert.assertEquals(drink, drinkList.getFavorite());
    }

    @Test
    public void setFavoriteButNotSetTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertNull(drinkList.getFavorite());
    }

    @Test
    public void listIngredientsTestButNotInList() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("Der von Ihnen genannt Drink ist mir leider nicht bekannt.",
                drinkList.listIngredients("1"));
    }

    @Test
    public void listIngredientsButNoIngredientsTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("Fuer Cola sind leider keine Zutaten gespeichert.", drinkList.listIngredients("Cola"));
    }

    @Test
    public void listIngredientsWithTwoIngrediencesTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("Kaffee enthält folgende Zutaten: 1 Kaffeelöffel Kaffeepulver und 250 Milliliter Wasser.",
                drinkList.listIngredients("Kaffee"));
    }

    @Test
    public void listIngredientsWithMoreThanTwoIngrediencesTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals(
                "Swimmingpool enthält folgende Zutaten: 14 Zentiliter granini Ananassaft, 2 Zentiliter Vodka, 2 Zentiliter Rum weiß, 2 Zentiliter Blue Curaçao, 3 Zentiliter Kokosnusscreme und 4 Zentiliter Sahne.",
                drinkList.listIngredients("Swimmingpool"));
    }

}
