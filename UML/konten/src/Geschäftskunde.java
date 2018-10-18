public class Geschäftskunde extends Kunde {
    private String firmenname;
    private Adresse domizilAdresse;

    Geschäftskunde(String firmenname, Adresse domizilAdresse) {
        super();
        this.firmenname = firmenname;
        this.domizilAdresse = domizilAdresse;
    }
}
