package domZdravlja;

public class Administrator extends Osoba {
    public Administrator(int id, String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
    }

    @Override
    public String toCSVString() {
        return getId() + "," + getIme() + "," + getPrezime() + "," + getJmbg() + "," + getPol().ordinal() + "," + getAdresa() + "," + getTelefon() + "," + getKorisnickoIme() + "," + getLozinka() + "," + getUloga().ordinal();
    }
}
