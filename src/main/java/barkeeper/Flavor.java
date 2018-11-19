package barkeeper;

public enum Flavor {
    SWEET("süß"), SOUR("sauer"), BITTER("bitter");

    private final String flavor;

    private Flavor(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }
    
}
