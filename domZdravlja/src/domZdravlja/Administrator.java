package domZdravlja;

public class Administrator extends Osoba {
    public Administrator(String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka) {
        super(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka);
        // Dodatna logika za Administratora može biti ovde
    }
}
