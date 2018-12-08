package simplebarkeeper;

import java.time.LocalTime;

/**
 * Enum which represents Daytimes.
 * @author Alexander Heinritzi
 */
public enum Daytime {
    MORNING(LocalTime.parse("08:00:00")), NOON(LocalTime.parse("12:00:00")), EVENING(LocalTime.parse("17:00:00")),
    ALLDAY(null);

    private final LocalTime startTime;

    private Daytime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        if (startTime.equals(Daytime.MORNING.getStartTime())) {
            return NOON.getStartTime();
        } else if (startTime.equals(Daytime.NOON.getStartTime())) {
            return EVENING.getStartTime();
        } else if (startTime.equals(Daytime.EVENING.getStartTime())) {
            return MORNING.getStartTime();
        }

        return null;
    }

}
