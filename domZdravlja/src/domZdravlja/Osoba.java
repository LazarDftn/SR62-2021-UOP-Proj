package domZdravlja;

public abstract class Osoba {
    protected int id;
    protected String ime;
    protected String prezime;
    protected String jmbg;
    protected Pol pol;
    protected String adresa;
    protected String telefon;
    protected String korisnickoIme;
    protected String lozinka;
    protected Uloga uloga;
    
    public Osoba(int id, String ime, String prezime, String jmbg, Pol pol, String adresa, String telefon, String korisnickoIme, String lozinka, Uloga uloga) {
        this.id = id;  
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.pol = pol;
        this.adresa = adresa;
        this.telefon = telefon;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.uloga = uloga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toCSVString() {
        return id + "," + ime + "," + prezime + "," + jmbg + "," + pol.ordinal() + "," + adresa + "," + telefon + "," + korisnickoIme + "," + lozinka + "," + uloga.ordinal();
    }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getJmbg() { return jmbg; }
    public void setJmbg(String jmbg) { this.jmbg = jmbg; }

    public Pol getPol() { return pol; }
    public void setPol(Pol pol) { this.pol = pol; }

    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getKorisnickoIme() { return korisnickoIme; }
    public void setKorisnickoIme(String korisnickoIme) { this.korisnickoIme = korisnickoIme; }

    public String getLozinka() { return lozinka; }
    public void setLozinka(String lozinka) { this.lozinka = lozinka; }

    public Uloga getUloga() { return uloga; }
    public void setUloga(Uloga uloga) { this.uloga = uloga; }
}

