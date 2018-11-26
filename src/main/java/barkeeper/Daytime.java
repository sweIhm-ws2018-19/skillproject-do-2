package main.java.barkeeper;

public enum Daytime {

    MORNING("morning"), NOON("noon"), EVENING("evening");

    private final String daytimeName;

    Daytime(String daytimeName) {
        this.daytimeName = daytimeName;
    }

    public String getDaytimeName() {
        return daytimeName;
    }

}
