package simplebarkeeper;

import org.junit.Assert;
import org.junit.Test;

import simplebarkeeper.Flavor;

public class FlavorTest {

    @Test
    public void getDaytime() {
        Flavor sweet = Flavor.SWEET;
        Flavor sour = Flavor.SOUR;
        Flavor bitter = Flavor.BITTER;
        Assert.assertEquals("süß", sweet.getFlavorName());
        Assert.assertEquals("sauer", sour.getFlavorName());
        Assert.assertEquals("bitter", bitter.getFlavorName());

    }
}
