package simplebarkeeper;

/**
 * Enum which represents Flavors.
 * 
 * @author Alexander Heinritzi
 *
 */
public enum Flavor {
    SWEET("süß"), SOUR("sauer"), BITTER("bitter");

    private final String flavorName;

    /**
     * Ctor for flavors.
     * 
     * @param flavorName The name of a flavor as string.
     */
    Flavor(String flavorName) {
        this.flavorName = flavorName;
    }

    /**
     * Getter for the name of a flavor as string.
     * 
     * @return The string representation of a flavor.
     */
    public String getFlavorName() {
        return flavorName;
    }

}
