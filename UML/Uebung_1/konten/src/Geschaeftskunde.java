public class Geschaeftskunde extends Kunde {
    private String firmenname;
    private Adresse domizilAdresse;

    Geschaeftskunde(String firmenname, Adresse domizilAdresse) {
        super();
        this.firmenname = firmenname;
        this.domizilAdresse = domizilAdresse;
    }
}
