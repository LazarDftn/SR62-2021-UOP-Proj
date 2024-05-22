package domZdravlja;

public class Administrator extends Osoba {
    public Administrator(String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka, Uloga uloga) {
        super(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka,uloga);
        // Dodatna logika za Administratora može biti ovde
    }
}
