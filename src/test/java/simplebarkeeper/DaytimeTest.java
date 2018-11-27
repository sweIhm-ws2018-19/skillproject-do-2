package simplebarkeeper;

import org.junit.Assert;
import org.junit.Test;

import simplebarkeeper.Daytime;

public class DaytimeTest {

    @Test
    public void getDaytime() {
        Daytime morning = Daytime.MORNING;
        Daytime noon = Daytime.NOON;
        Daytime evening = Daytime.EVENING;
        Assert.assertEquals("morning", morning.getDaytimeName());
        Assert.assertEquals("noon", noon.getDaytimeName());
        Assert.assertEquals("evening", evening.getDaytimeName());
    }
}
