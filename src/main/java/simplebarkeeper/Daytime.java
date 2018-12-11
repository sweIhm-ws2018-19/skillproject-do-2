package simplebarkeeper;

import java.time.LocalTime;

/**
 * Enum which represents Daytimes.
 * 
 * @author Alexander Heinritzi
 */
public enum Daytime {
    MORNING(LocalTime.parse("08:00:00"), 4), NOON(LocalTime.parse("12:00:00"), 5),
    EVENING(LocalTime.parse("17:00:00"), 15), ALLDAY(LocalTime.parse("00:00:00"), 24);

    private final LocalTime startTime;
    private final long duration;

    private Daytime(LocalTime startTime, int duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return startTime.plusHours(duration);
    }

}
