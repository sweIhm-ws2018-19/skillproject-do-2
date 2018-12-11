package simplebarkeeper;

import org.junit.Assert;
import org.junit.Test;

public class FlavorTest {

    @Test
    public void getDaytime() {
        Flavour sweet = Flavour.SWEET;
        Flavour sour = Flavour.SOUR;
        Flavour bitter = Flavour.BITTER;
        
        Assert.assertEquals("SWEET", sweet.toString());
        Assert.assertEquals("SOUR", sour.toString());
        Assert.assertEquals("BITTER", bitter.toString());

    }
}
