package simplebarkeeper;

import org.junit.Assert;
import org.junit.Test;

import simplebarkeeper.Flavour;

public class FlavorTest {

    @Test
    public void getDaytime() {
        Flavour sweet = Flavour.SWEET;
        Flavour sour = Flavour.SOUR;
        Flavour bitter = Flavour.BITTER;
        Assert.assertEquals("süß", sweet.getFlavorName());
        Assert.assertEquals("sauer", sour.getFlavorName());
        Assert.assertEquals("bitter", bitter.getFlavorName());

    }
}
