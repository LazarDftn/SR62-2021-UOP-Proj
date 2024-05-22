package domZdravlja;

import java.util.ArrayList;

public class ZdravstveniKarton {
    private ArrayList<Termin> termini;
    private Pacijent pacijent;

    public void dodajTermin(Termin noviTermin) {
        termini.add(noviTermin);
    }

    public void ukloniTermin(Termin termin) {
        termini.remove(termin);
    }

    public ArrayList<Termin> getTermini() {
        return new ArrayList<>(termini);
    }
}


