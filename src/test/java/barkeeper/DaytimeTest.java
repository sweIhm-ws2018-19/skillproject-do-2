package test.java.barkeeper;

import org.junit.Assert;
import org.junit.Test;

import main.java.barkeeper.Daytime;

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
