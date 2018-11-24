package main.java.barkeeper;

public enum Daytime {

    MORNING("morning"), NOON("noon"), EVENING("evening");

    private final String daytime;

    Daytime(String daytime) {
        this.daytime = daytime;
    }

    public String getDaytime() {
        return daytime;
    }

}
