package domZdravlja;

public abstract class Osoba {
    protected String ime;
    protected String prezime;
    protected String jmbg;
    protected char pol;
    protected String adresa;
    protected String telefon;
    protected String korisnickoIme;
    protected String lozinka;
    protected String uloga;

    public Osoba(String ime, String prezime, String jmbg, char pol, String adresa, String telefon, String korisnickoIme, String lozinka, String uloga) {
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

        public String getIme() {
            return ime;
        }

        public String getPrezime() {
            return prezime;
        }

        public String getJMBG() {
            return jmbg;
        }

        public char getPol() {
            return pol;
        }

        public String getAdresa() {
            return adresa;
        }

        public String getTelefon() {
            return telefon;
        }

        public String getKorisnickoIme() {
            return korisnickoIme;
        }

        public String getLozinka() {
            return lozinka;
        }

        public String getUloga() {
            return uloga;
        }

        public void setIme(String ime) {
            this.ime = ime;
        }

        public void setPrezime(String prezime) {
            this.prezime = prezime;
        }

        public void setJMBG(String jmbg) {
            this.jmbg = jmbg;
        }

        public void setPol(char pol) {
            this.pol = pol;
        }

        public void setAdresa(String adresa) {
            this.adresa = adresa;
        }

        public void setTelefon(String telefon) {
            this.telefon = telefon;
        }

        public void setKorisnickoIme(String korisnickoIme) {
            this.korisnickoIme = korisnickoIme;
        }

        public void setLozinka(String lozinka) {
            this.lozinka = lozinka;
        }
    }


