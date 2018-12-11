
package simplebarkeeper;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DrinkTest {

    @Test
    public void getNameTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertEquals(name, drink.getName());
    }

    @Test
    public void getFlavourTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertEquals(flavour, drink.getFlavour());
    }

    @Test
    public void getDaytimeTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertEquals(daytime, drink.getDaytime());
    }

    @Test
    public void getContainsAlcoholTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertTrue(drink.getContainsAlcohol());
    }

    @Test
    public void getIngridientsTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertEquals(ingredients, drink.getIngredients());
    }

    @Test
    public void getStepsTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertEquals(step1, drink.getSteps().get(0));
        Assert.assertEquals(step2, drink.getSteps().get(1));
    }

    @Test
    public void toStringTest() {
        String name = "TestDrinkName";
        Flavour flavour = Flavour.BITTER;
        Daytime daytime = Daytime.MORNING;
        Boolean containsAlcohol = true;
        String ingredients = "1 ml Rum, 1ml Cola";

        List<String> steps = new ArrayList<>();
        String step1 = "Step 1: drink";
        String step2 = "Step 2: something";
        steps.add(step1);
        steps.add(step2);

        Drink drink = new Drink(name, flavour.toString(), daytime.toString(), containsAlcohol.toString(), ingredients,
                steps);

        Assert.assertEquals(
                "drinkName: TestDrinkName; flavour: BITTER; daytime: MORNING; containsAlcohol: true\n# ingredients: 1 ml Rum, 1ml Cola\n## step 1: Step 1: drink\n## step 2: Step 2: something\n\n",
                drink.toString());
    }

}
