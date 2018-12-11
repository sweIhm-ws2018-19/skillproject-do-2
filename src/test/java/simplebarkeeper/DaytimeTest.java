package simplebarkeeper;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class DaytimeTest {

    @Test
    public void getStartTimeTest() {
        LocalTime morningStartTime = LocalTime.parse("08:00");
        LocalTime noonStartTime = LocalTime.parse("12:00");
        LocalTime eveningStartTime = LocalTime.parse("17:00");
        LocalTime alldayStartTime = LocalTime.parse("00:00");

        Assert.assertEquals(morningStartTime, Daytime.MORNING.getStartTime());
        Assert.assertEquals(noonStartTime, Daytime.NOON.getStartTime());
        Assert.assertEquals(eveningStartTime, Daytime.EVENING.getStartTime());
        Assert.assertEquals(alldayStartTime, Daytime.ALLDAY.getStartTime());
    }

    @Test
    public void getEndTimeTest() {
        LocalTime morninEndTime = LocalTime.parse("12:00");
        LocalTime noonEndTime = LocalTime.parse("17:00");
        LocalTime eveningEndTime = LocalTime.parse("08:00");
        LocalTime alldayEndTime = LocalTime.parse("00:00");

        Assert.assertEquals(morninEndTime, Daytime.MORNING.getEndTime());
        Assert.assertEquals(noonEndTime, Daytime.NOON.getEndTime());
        Assert.assertEquals(eveningEndTime, Daytime.EVENING.getEndTime());
        Assert.assertEquals(alldayEndTime, Daytime.ALLDAY.getEndTime());
    }
}
