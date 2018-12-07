package simplebarkeeper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class ListOfDrinksTest {

    @Test
    public void getDrinkByNameTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("rote schorle", drinkList.getDrinkByName("rote schorle").getName());
        Assert.assertEquals(Flavour.SWEET, drinkList.getDrinkByName("rote schorle").getFlavor());
        Assert.assertEquals(Daytime.NOON, drinkList.getDrinkByName("rote schorle").getDaytime());
        Assert.assertFalse(drinkList.getDrinkByName("rote schorle").getContainsAlcohol());
        Assert.assertTrue(drinkList.getDrinkByName("rote schorle").getIngredients().isEmpty());
    }

    @Test
    public void cTorWithoutInitialListInResourcesTest() throws IOException {
        URL url = this.getClass().getResource("/initialDrinkList.json");
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        file.setReadable(false);
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals(0, drinkList.getSize());

        file.setReadable(true);
    }

    @Test
    public void getFavoriteTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Drink drink = drinkList.getDrinkByName("cola");
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
        Assert.assertEquals("Der von Ihnen genannte Drink ist mir leider nicht bekannt.",
                drinkList.listIngredients("1"));
    }

    @Test
    public void listIngredientsButNoIngredientsTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("Für cola sind leider keine Zutaten gespeichert.", drinkList.listIngredients("cola"));
    }

    @Test
    public void listIngredientsWithTwoIngrediencesTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals("kaffee enthält folgende Zutaten: 1 Kaffeelöffel Kaffeepulver und 250 Milliliter Wasser.",
                drinkList.listIngredients("kaffee"));
    }

    @Test
    public void listIngredientsWithMoreThanTwoIngrediencesTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        Assert.assertEquals(
                "swimmingpool enthält folgende Zutaten: 14 Zentiliter granini Ananassaft, 2 Zentiliter Vodka, 2 Zentiliter Rum weiß, 2 Zentiliter Blue Curaçao, 3 Zentiliter Kokosnusscreme und 4 Zentiliter Sahne.",
                drinkList.listIngredients("swimmingpool"));
    }

}
