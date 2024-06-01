package domZdravlja;

public class Pacijent extends Osoba {
    private ZdravstveniKarton zdravstveniKarton;

    public Pacijent(int id, String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
    }

    public ZdravstveniKarton getZdravstveniKarton() {
        return zdravstveniKarton;
    }

    public void setZdravstveniKarton(ZdravstveniKarton zdravstveniKarton) {
        this.zdravstveniKarton = zdravstveniKarton;
    }

    public void dodajTermin(Termin termin) {
        if (this.zdravstveniKarton != null) {
            this.zdravstveniKarton.dodajTermin(termin);
        }
    }

    public void ukloniTermin(Termin termin) {
        if (this.zdravstveniKarton != null) {
            this.zdravstveniKarton.ukloniTermin(termin);
        }
    }

    @Override
    public String toCSVString() {
      
        return getId() + "," + getIme() + "," + getPrezime() + "," + getJmbg() + "," + getPol().ordinal() + "," + getAdresa() + "," + getTelefon() + "," + getKorisnickoIme() + "," + getLozinka() + "," + getUloga().ordinal();
    }
}
