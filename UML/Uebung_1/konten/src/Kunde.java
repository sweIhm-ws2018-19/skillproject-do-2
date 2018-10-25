public abstract class Kunde {
    private List<Konto> konto = new ArrayList<>();

    // Jeder Kunde hat min. ein Konto.
    // Da Konto vor Kunde nicht existieren kann,
    // wird jeder Kunde mit neuem Konto initialisiert.
    Kunde() {
        this.konto.add(new Konto(this));
    }

}
