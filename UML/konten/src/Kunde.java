public abstract class Kunde {
    private Konto[] konto;

    // Jeder Kunde hat min. ein Konto.
    // Da Konto vor Kunde nicht existieren kann,
    // wird jeder Kunde mit neuem Konto initialisiert.
    Kunde() {
        konto[0] = new Konto(this);
    }

}
