public class Konto {
    private String bezeichnung;
    private List<Kunde> zeichnungsberechtigter = new ArrayList<>();

    // Konten haben min. einen Kunden,
    // Kunde kann aber schon exisiteren.
    Konto(Kunde zeichnungsberechtigter) {
        this.zeichnungsberechtigter.add(zeichnungsberechtigter);
    }

    public GeldBetrag saldo() {
        return ...
    }

    public einzahlen(GeldBetrag eingbetrag) {
        return ...
    }
}
