package domZdravlja;

public class Lekar extends Osoba {
    public Lekar(String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka) {
        super(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, "Lekar");
    }
    public Termin kreirajTermin(Pacijent pacijent, String datum, String opisTerapije) {
        Termin noviTermin = new Termin(this, pacijent, datum, Status.SLOBODAN, opisTerapije);

        return noviTermin;
    }

    public void azurirajTermin(Termin termin, String noviDatum, String noviOpisTerapije) {
        termin.setDatum(noviDatum);
        termin.setOpisTerapije(noviOpisTerapije);

    }


    public void otkaziTermin(Termin termin) {
        termin.setStatus(Status.OTKAZAN);

    }
}