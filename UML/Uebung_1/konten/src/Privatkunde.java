public class Privatkunde extends Kunde {
    private String vorname;
    private String nachname;
    private Adresse postAdresse;

    Privatkunde(String vorname, String nachname, Adresse postAdresse) {
        super();
        this.vorname = vorname;
        this.nachname = nachname;
        this.postAdresse = postAdresse;
    }
}
