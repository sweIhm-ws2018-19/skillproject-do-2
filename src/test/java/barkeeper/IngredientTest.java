package barkeeper;

import org.junit.Assert;
import org.junit.Test;

import simpleBarkeeper.Ingredient;

public class IngredientTest {
    Ingredient test = new Ingredient("Cola", "100 Milliliter");

    @Test
    public void getNameTest() {
        Assert.assertEquals("Cola", test.getName());
    }

    @Test
    public void getAmountTest() {
        Assert.assertEquals("100 Milliliter", test.getAmount());

    }

    @Test
    public void toStringTest() {
        Assert.assertEquals("ingredient: Cola; amount: 100 Milliliter", test.toString());
    }
}
