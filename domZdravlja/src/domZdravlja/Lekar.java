package domZdravlja;

public class Lekar extends Osoba {
    public Lekar(int id,String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka, Uloga uloga) {
        super(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
    }

    public Termin kreirajTermin(int terminId, Pacijent pacijent, String datum, String opisTerapije) {
  
        Termin noviTermin = new Termin(terminId, this, pacijent, datum, Status.SLOBODAN, opisTerapije);
        return noviTermin;
    }

    public void azurirajTermin(Termin termin, String noviDatum, String noviOpisTerapije) {
        termin.setDatum(noviDatum);
        termin.setOpisTerapije(noviOpisTerapije);
    
    }

    public void otkaziTermin(Termin termin) {
        termin.setStatus(Status.OTKAZAN);
       
    }

    @Override
    public String toCSVString() {
        return getId() + "," + getIme() + "," + getPrezime() + "," + getJmbg() + "," + getPol().ordinal() + "," + getAdresa() + "," + getTelefon() + "," + getKorisnickoIme() + "," + getLozinka() + "," + getUloga().ordinal();
    }
}
