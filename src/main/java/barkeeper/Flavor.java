package main.java.barkeeper;

public enum Flavor {
    SWEET("süß"), SOUR("sauer"), BITTER("bitter");

    private final String flavorName;

    private Flavor(String flavorName) {
        this.flavorName = flavorName;
    }

    public String getflavorName() {
        return flavorName;
    }
    
}
