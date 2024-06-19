package domZdravlja;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ZdravstveniKarton {
	
    private int id; 
    private ArrayList<Termin> termini;
    private Pacijent pacijent;

    public ZdravstveniKarton(int id, Pacijent pacijent) {
        this.id = id;
        this.pacijent = pacijent;
        this.termini = new ArrayList<>();
    }

    public void dodajTermin(Termin noviTermin) {
        termini.add(noviTermin);
    }

    public void ukloniTermin(Termin termin) {
        termini.remove(termin);
    }

    public ArrayList<Termin> getTermini() {
        return new ArrayList<>(termini);
    }

    public void setTermini(List<Termin> termini) {
        this.termini = new ArrayList<>(termini);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public String getTerminiIds() {
        return termini.stream()
            .map(Termin::getId)
            .map(String::valueOf)
            .collect(Collectors.joining(";"));
    }

    public String toCSVString() {
        String terminiIds = getTerminiIds();
        return id + "," + pacijent.getId() + "," + terminiIds;
    }
}
