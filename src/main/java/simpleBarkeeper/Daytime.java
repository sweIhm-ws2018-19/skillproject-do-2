package simpleBarkeeper;

/**
 * Enum which represents Daytimes.
 * 
 * @author Alexander Heinritzi
 *
 */
public enum Daytime {

    MORNING("morning"), NOON("noon"), EVENING("evening");

    private final String daytimeName;

    /**
     * Ctor for Daytimes.
     * 
     * @param daytimeName The name of the daytime (morning, noon, evening).
     */
    Daytime(String daytimeName) {
        this.daytimeName = daytimeName;
    }

    /**
     * Getter for the daytimeName.
     * 
     * @return The name of the daytime.
     */
    public String getDaytimeName() {
        return daytimeName;
    }

}
