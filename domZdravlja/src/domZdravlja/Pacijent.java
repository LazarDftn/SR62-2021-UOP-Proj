package domZdravlja;

public class Pacijent extends Osoba {
    private ZdravstveniKarton zdravstveniKarton;

    public Pacijent(String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka, ZdravstveniKarton zdravstveniKarton) {
        super(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, "Pacijent");
        this.zdravstveniKarton = zdravstveniKarton;
    }

    public ZdravstveniKarton getZdravstveniKarton() {
        return zdravstveniKarton;
    }

    public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
        this.zdravstveniKarton = zdravstveniKarton;
    }


    public void dodajTermin(Termin termin) {
        this.zdravstveniKarton.dodajTermin(termin);
    }

    public void ukloniTermin(Termin termin) {
        this.zdravstveniKarton.ukloniTermin(termin);
    }
}
