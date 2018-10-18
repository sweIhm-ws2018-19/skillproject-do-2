
public class Konto {
    private String bezeichnung;
    private Kunde[] zeichnungsberechtigter;

    // Konten haben min. einen Kunden,
    // Kunde kann aber schon exisiteren.
    Konto(Kunde zeichnungsberechtigter) {
        this.zeichnungsberechtigter[0] = zeichnungsberechtigter;
    }

    public GeldBetrag saldo() {
        return ...
    }

    public einzahlen(GeldBetrag eing.betrag) {
        return ...
    }
}
