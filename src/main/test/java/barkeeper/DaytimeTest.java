package barkeeper;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Test;

public class DaytimeTest {


    @Test
    public void getDaytime() {
        Daytime morning = Daytime.MORNING;
        Daytime noon = Daytime.NOON;
        Daytime evening = Daytime.EVENING;
        Assert.assertEquals("morning", morning.getDaytime());
        Assert.assertEquals("noon", noon.getDaytime());
        Assert.assertEquals("evening", evening.getDaytime());
    }
}
