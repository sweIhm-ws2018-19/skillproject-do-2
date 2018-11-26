package test.java.barkeeper;

import org.junit.Assert;
import org.junit.Test;

import main.java.barkeeper.Flavor;

public class FlavorTest {

    @Test
    public void getDaytime() {
        Flavor sweet = Flavor.SWEET;
        Flavor sour = Flavor.SOUR;
        Flavor bitter = Flavor.BITTER;
        Assert.assertEquals("süß", sweet.getFlavor());
        Assert.assertEquals("sauer", sour.getFlavor());
        Assert.assertEquals("bitter", bitter.getFlavor());

    }
}