package simplebarkeeper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class ListOfDrinksTest {



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
				drinkList.getRandomDrinkByFlavour("HONEY", "true", LocalTime.parse("08:00:00")));
	}

	@Test
	public void getRandomDrinkByFlavourWithAlcoholTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		String drinkWithAlcoholName = drinkList.getRandomDrinkByFlavour("BITTER", "true", LocalTime.parse("08:00:00"));
		int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
		drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
		Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

		Assert.assertTrue(drinkWithAlcohol.getContainsAlcohol());
		Assert.assertEquals(Flavour.BITTER, drinkWithAlcohol.getFlavour());
	}

	@Test
	public void getRandomDrinkByFlavourWithoutAlcoholTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		String drinkWithoutAlcoholName = drinkList.getRandomDrinkByFlavour("SWEET", "false",
				LocalTime.parse("08:00:00"));
		int positionLastSpace = drinkWithoutAlcoholName.lastIndexOf(": ");
		drinkWithoutAlcoholName = drinkWithoutAlcoholName.substring(positionLastSpace + 2);
		Drink drinkWithoutAlcohol = drinkList.getDrink(drinkWithoutAlcoholName);

		Assert.assertFalse(drinkWithoutAlcohol.getContainsAlcohol());
		Assert.assertEquals(Flavour.SWEET, drinkWithoutAlcohol.getFlavour());
	}

	@Test
	public void getRandomDrinkByFlavourWithAlcoholButHasNoneAtThisTimeTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertEquals("Zu dieser Auswahl ist mir zur aktuellen Uhrzeit leider kein Drink bekannt",
				drinkList.getRandomDrinkByFlavour("SOUR", "true", LocalTime.parse("08:00:00")));
	}

	@Test
	public void getRandomDrinkByAlcoholTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		String drinkWithAlcoholName = drinkList.getRandomDrinkByAlcohol("true", LocalTime.parse("08:00:00"));
		int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
		drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
		Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

		Assert.assertTrue(drinkWithAlcohol.getContainsAlcohol());
	}

	@Test
	public void getRandomDrinkByAlcoholWithoutAlcoholTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		String drinkWithoutAlcoholName = drinkList.getRandomDrinkByAlcohol("false", LocalTime.parse("08:00:00"));
		int positionLastSpace = drinkWithoutAlcoholName.lastIndexOf(": ");
		drinkWithoutAlcoholName = drinkWithoutAlcoholName.substring(positionLastSpace + 2);
		Drink drinkWithoutAlcohol = drinkList.getDrink(drinkWithoutAlcoholName);

		Assert.assertFalse(drinkWithoutAlcohol.getContainsAlcohol());
	}

	@Test
	public void getRandomDrinkByIngredientWithAlcoholTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		String drinkWithAlcoholName = drinkList.getRandomDrinkByIngredient("rum", "true", LocalTime.parse("08:00:00"));
		int positionLastSpace = drinkWithAlcoholName.lastIndexOf(": ");
		drinkWithAlcoholName = drinkWithAlcoholName.substring(positionLastSpace + 2);
		Drink drinkWithAlcohol = drinkList.getDrink(drinkWithAlcoholName);

		Assert.assertTrue(drinkWithAlcohol.getContainsAlcohol());
		Assert.assertTrue(drinkWithAlcohol.getIngredients().toLowerCase().contains("rum"));
	}

	@Test
	public void getRandomDrinkByIngredientWithoutAlcoholTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		String drinkWithAlcoholName = drinkList.getRandomDrinkByIngredient("cola", "false",
				LocalTime.parse("08:00:00"));
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
				drinkList.getRandomDrinkByIngredient("kaffee", "true", LocalTime.parse("08:00:00")));
	}

	@Test
	public void drinkFitsDaytimeMorningTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertTrue(drinkList.drinkFitsDaytime(drinkList.getDrink("kaffee"), LocalTime.parse("08:00:01")));
	}

	@Test
	public void drinkFitsDaytimeMorningButDoesNotFitTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertFalse(drinkList.drinkFitsDaytime(drinkList.getDrink("kaffee"), LocalTime.parse("12:00:01")));
	}

	@Test
	public void drinkFitsDaytimeNoonTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertTrue(drinkList.drinkFitsDaytime(drinkList.getDrink("rote schorle"), LocalTime.parse("12:00:01")));
	}

	@Test
	public void drinkFitsDaytimeNoonButDoesNotFitTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertFalse(drinkList.drinkFitsDaytime(drinkList.getDrink("rote schorle"), LocalTime.parse("17:00:01")));
	}

	@Test
	public void drinkFitsDaytimeEveningBeforeMidnightTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertTrue(
				drinkList.drinkFitsDaytime(drinkList.getDrink("sex on the beach"), LocalTime.parse("23:59:59")));
	}

	@Test
	public void drinkFitsDaytimeEveningAfterMidnightTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertTrue(
				drinkList.drinkFitsDaytime(drinkList.getDrink("sex on the beach"), LocalTime.parse("00:00:01")));
	}

	@Test
	public void drinkFitsDaytimeEveningAtMidnightTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertTrue(
				drinkList.drinkFitsDaytime(drinkList.getDrink("sex on the beach"), LocalTime.parse("00:00:00")));
	}

	@Test
	public void drinkFitsDaytimeEveningButDoesNotFitTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertFalse(
				drinkList.drinkFitsDaytime(drinkList.getDrink("sex on the beach"), LocalTime.parse("08:00:00")));
	}

	@Test
	public void getSizeTest() throws IOException {
		ListOfDrinks drinkList = new ListOfDrinks();

		Assert.assertEquals(28, drinkList.getSize());
	}

}
