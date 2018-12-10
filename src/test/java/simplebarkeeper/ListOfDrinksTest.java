package simplebarkeeper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

public class ListOfDrinksTest {

    @Test
    public void cTorWithoutInitialListInResourcesTest() throws IOException {
        URL url = this.getClass().getResource("/initialDrinkList.json");
        String pathWithoutPercents = url.getFile().replace("%20", " ");
        File file = new File(pathWithoutPercents);

        file.setReadable(false);
        ListOfDrinks drinkList = new ListOfDrinks();
        int sizeOfDrinkList = drinkList.getSize();
        file.setReadable(true);

        Assert.assertEquals(0, sizeOfDrinkList);

    }

    @Test
    public void setFavoriteTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("cola wurde als dein neuer Lieblingsdrink gespeichert", drinkList.setFavorite("cola"));
    }

    @Test
    public void setAndGetFavoriteTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();
        drinkList.setFavorite("cola");

        Assert.assertEquals("Dein Lieblingsdrink ist cola", drinkList.getFavorite());
    }

    @Test
    public void setFavoriteButNotInListTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Dieser Drink ist mir leider nicht bekannt", drinkList.setFavorite("notInList"));
    }

    @Test
    public void getFavoriteButNotSetTest() throws IOException {
        URL url = this.getClass().getResource("/favourite.txt");

        if (url != null) {
            String pathWithoutPercents = url.getFile().replace("%20", " ");
            File file = new File(pathWithoutPercents);
            file.delete();
        }
        
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Tut mir leid, bisher wurde keine Favourit festgelegt", drinkList.getFavorite());
    }

    @Test
    public void getIngredientsButNotInListTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Dieser Drink ist mir leider nicht bekannt", drinkList.getIngredients("notInList"));
    }

    @Test
    public void getIngredientsButHasNoneTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Dieser Drink enthält keine weiteren Zutaten", drinkList.getIngredients("cola"));
    }

    @Test
    public void getIngredientsTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals(
                "sex on the beach enthält folgende Zutaten: 4 Zentiliter granini Orange, 3 Zentiliter granini Pfirsich, 3 Zentiliter granini Ananas, 3 Zentiliter granini Cranberry und 4 Zentiliter Wodka",
                drinkList.getIngredients("sex on the beach"));
    }

    @Test
    public void getRandomDrinkByFlavourButFlavourDoesNotExistTest() {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Dieser Geschmack ist mir leider nicht bekannt",
                drinkList.getRandomDrinkByFlavour("HONEY", "true"));
    }

    @Test
    public void getRandomDrinkByFlavourWithAlcoholTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        String drinkWithAlcoholName = drinkList.getRandomDrinkByFlavour("BITTER", "true");
        int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
        drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
        Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

        Assert.assertTrue(drinkWithAlcohol.getContainsAlcohol());
        Assert.assertEquals(Flavour.BITTER, drinkWithAlcohol.getFlavour());
    }

    @Test
    public void getRandomDrinkByFlavourWithoutAlcoholTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        String drinkWithoutAlcoholName = drinkList.getRandomDrinkByFlavour("BITTER", "false");
        int positionLastSpace = drinkWithoutAlcoholName.lastIndexOf(": ");
        drinkWithoutAlcoholName = drinkWithoutAlcoholName.substring(positionLastSpace + 2);
        Drink drinkWithoutAlcohol = drinkList.getDrink(drinkWithoutAlcoholName);

        Assert.assertFalse(drinkWithoutAlcohol.getContainsAlcohol());
        Assert.assertEquals(Flavour.BITTER, drinkWithoutAlcohol.getFlavour());
    }

    @Test
    public void getRandomDrinkByFlavourWithAlcoholButHasNoneAtThisTimeTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt",
                drinkList.getRandomDrinkByFlavour("SOUR", "true"));
    }

    @Test
    public void getRandomDrinkByAlcoholTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        String drinkWithAlcoholName = drinkList.getRandomDrinkByAlcohol("true");
        int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
        drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
        Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

        Assert.assertTrue(drinkWithAlcohol.getContainsAlcohol());
    }

    @Test
    public void getRandomDrinkByAlcoholWithoutAlcoholTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        String drinkWithoutAlcoholName = drinkList.getRandomDrinkByAlcohol("false");
        int positionLastSpace = drinkWithoutAlcoholName.lastIndexOf(": ");
        drinkWithoutAlcoholName = drinkWithoutAlcoholName.substring(positionLastSpace + 2);
        Drink drinkWithoutAlcohol = drinkList.getDrink(drinkWithoutAlcoholName);

        Assert.assertFalse(drinkWithoutAlcohol.getContainsAlcohol());
    }

    @Test
    public void getRandomDrinkByIngredientWithAlcoholTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        String drinkWithAlcoholName = drinkList.getRandomDrinkByIngredient("rum", "true");
        int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
        drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
        Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

        Assert.assertTrue(drinkWithAlcohol.getContainsAlcohol());
        Assert.assertTrue(drinkWithAlcohol.getIngredients().toLowerCase().contains("rum"));
    }

    @Test
    public void getRandomDrinkByIngredientWithoutAlcoholTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        String drinkWithAlcoholName = drinkList.getRandomDrinkByIngredient("cola", "false");
        int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
        drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
        Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

        Assert.assertFalse(drinkWithAlcohol.getContainsAlcohol());
        Assert.assertTrue(drinkWithAlcohol.getIngredients().toLowerCase().contains("cola"));
    }

    @Test
    public void getRandomDrinkByIngredientWithAlcoholButHasNoneAtThisTimeTest() throws IOException {
        ListOfDrinks drinkList = new ListOfDrinks();

        Assert.assertEquals("Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt",
                drinkList.getRandomDrinkByIngredient("kaffee", "true"));
    }

}
