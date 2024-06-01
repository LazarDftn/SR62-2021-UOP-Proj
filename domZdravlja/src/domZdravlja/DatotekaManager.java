package domZdravlja;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatotekaManager {
    
    private static final String DATA_FOLDER = "fajlovi";
    private static final Path KORISNICI_PUTANJA = Paths.get(DATA_FOLDER, "korisnici.csv");
    private static final Path TERMINI_PUTANJA = Paths.get(DATA_FOLDER, "termini.csv");
    private static final Path KARTONI_PUTANJA = Paths.get(DATA_FOLDER, "zdravstveniKartoni.csv");
    
    private static final Map<Integer, Osoba> korisnici = new HashMap<>();
    private static final Map<Integer, Termin> termini = new HashMap<>();
    private static final Map<Integer, ZdravstveniKarton> kartoni = new HashMap<>();
    
    private static int nextUserId = 1; 
    private static int nextTerminId = 1; 
    private static int nextKartonId = 1; 
    
    private static Osoba ulogovanKorisnik = null;

    public static Osoba getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    public static void setUlogovanKorisnik(Osoba ulogovanKorisnik) {
        DatotekaManager.ulogovanKorisnik = ulogovanKorisnik;
    }

    public static void ucitaj() {
        try {
            ucitajKorisnike();
            ucitajTermine();
            ucitajKartone();
        } catch (IOException e) {
            System.err.println("Greška prilikom učitavanja podataka: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void ucitajKorisnike() throws IOException {
        List<String> linije = Files.readAllLines(KORISNICI_PUTANJA);
        for (String linija : linije) {
            try {
                String[] info = linija.split(",");
                int id = Integer.parseInt(info[0]);
                String korisnickoIme = info[1];
                String lozinka = info[2];
                String ime = info[3];
                String prezime = info[4];
                String jmbg = info[5];
                Pol pol = Pol.values()[Integer.parseInt(info[6])];
                String adresa = info[7];
                String telefon = info[8];
                Uloga uloga = Uloga.values()[Integer.parseInt(info[9])];
                
                Osoba osoba = null; 
                if (uloga == Uloga.ADMIN) {
                    osoba = new Administrator(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
                } else if (uloga == Uloga.PACIJENT) {
                    osoba = new Pacijent(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
                } else if (uloga == Uloga.LEKAR) {
                    osoba = new Lekar(id, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
                }
                if (osoba != null) {
                    korisnici.put(id, osoba);
                }
            } catch (Exception e) {
                System.err.println("Nevalidan unos u CSV fajlu: " + e.getMessage());
            }
            
        }
    

    public void sacuvajKorisnike() throws IOException {
        List<String> linije = new ArrayList<>();
        for (Osoba osoba : korisnici.values()) {
            linije.add(osoba.toCSVString());
        }
        Files.write(KORISNICI_PUTANJA, linije);
    }

    public void dodajKorisnika(Osoba osoba) {
        int id = nextId++;
        osoba.setId(id);  
        korisnici.put(id, osoba);
        try {
            sacuvajKorisnike();
        } catch (IOException e) {
            System.err.println("Greška prilikom čuvanja korisnika: " + e.getMessage());
        }
    }

    public void azurirajKorisnika(int id, Osoba novaOsoba) {
        if (korisnici.containsKey(id)) {
            korisnici.put(id, novaOsoba);
            try {
                sacuvajKorisnike();
            } catch (IOException e) {
                System.err.println("Greška prilikom ažuriranja korisnika: " + e.getMessage());
            }
        }
    }

    public void obrisiKorisnika(int id) {
        if (korisnici.containsKey(id)) {
            korisnici.remove(id);
            try {
                sacuvajKorisnike();
            } catch (IOException e) {
                System.err.println("Greška prilikom brisanja korisnika: " + e.getMessage());
            }
        }
    }

    public static boolean ulogujSe(String kIme, String lozinka) {
        for (Osoba osoba : korisnici.values()) {
            if (osoba.getKorisnickoIme().equals(kIme) && osoba.getLozinka().equals(lozinka)) {
                ulogovanKorisnik = osoba;
                System.out.println("Tip korisnika " + osoba.getUloga());
                return true;
            }
        }
        return false;
    }

    public void sacuvajTermine() throws IOException {
        List<String> linije = new ArrayList<>();
        for (Termin termin : termini.values()) {
            linije.add(termin.toCSVString());
        }
        Files.write(TERMINI_PUTANJA, linije);
    }
    private static void ucitajTermine() throws IOException {
        List<String> linije = Files.readAllLines(TERMINI_PUTANJA);
        for (String linija : linije) {
            try {
                String[] info = linija.split(",");
                int id = Integer.parseInt(info[0]);
                int lekarId = Integer.parseInt(info[1]);
                int pacijentId = Integer.parseInt(info[2]);
                String datum = info[3];
                Status status = Status.values()[Integer.parseInt(info[4])];
                String opisTerapije = info[5];

                Lekar lekar = (Lekar) korisnici.get(lekarId);
                Pacijent pacijent = (Pacijent) korisnici.get(pacijentId);

                if (lekar != null && pacijent != null) {
                    Termin noviTermin = new Termin(id, lekar, pacijent, datum, status, opisTerapije);
                    termini.put(id, noviTermin);
                }
            } catch (Exception e) {
                System.err.println("Nevalidan unos u CSV fajlu za termine: " + e.getMessage());
            }
        }
    }


    public void dodajTermin(Termin termin) {
        int id = nextTerminId++;
        termin.setId(id);
        termini.put(id, termin);
        sacuvajTermine();
    }

    public void azurirajTermin(int id, Termin noviTermin) {
        if (termini.containsKey(id)) {
            termini.put(id, noviTermin);
            sacuvajTermine();
        }
    }

    public void obrisiTermin(int id) {
        if (termini.containsKey(id)) {
            termini.remove(id);
            sacuvajTermine();
        }
    }

    private static void ucitajKartone() throws IOException {
        List<String> linije = Files.readAllLines(KARTONI_PUTANJA);
        for (String linija : linije) {
            try {
                String[] info = linija.split(",");
                int id = Integer.parseInt(info[0]);
                int pacijentId = Integer.parseInt(info[1]);
                String[] terminIds = info[2].split(";");
                List<Termin> terminiList = new ArrayList<>();
                for (String terminId : terminIds) {
                    if (!terminId.isEmpty()) {
                        int tid = Integer.parseInt(terminId);
                        if (termini.containsKey(tid)) {
                            terminiList.add(termini.get(tid));
                        }
                    }
                }

                Pacijent pacijent = (Pacijent) korisnici.get(pacijentId);
                if (pacijent != null) {
                    ZdravstveniKarton karton = new ZdravstveniKarton(id, pacijent);
                    karton.setTermini(terminiList);
                    kartoni.put(id, karton);
                    pacijent.setZdravstveniKarton(karton);
                }
            } catch (Exception e) {
                System.err.println("Nevalidan unos u CSV fajlu za zdravstvene kartone: " + e.getMessage());
            }
        }
    }

    public void sacuvajKartone() throws IOException {
        List<String> linije = new ArrayList<>();
        for (ZdravstveniKarton karton : kartoni.values()) {
            linije.add(karton.toCSVString());
        }
        Files.write(KARTONI_PUTANJA, linije);
    }

    public void dodajKarton(ZdravstveniKarton karton) {
        int id = nextKartonId++;
        karton.setId(id);
        kartoni.put(id, karton);
        sacuvajKartone();
    }

    public void azurirajKarton(int id, ZdravstveniKarton noviKarton) {
        if (kartoni.containsKey(id)) {
            kartoni.put(id, noviKarton);
            sacuvajKartone();
        }
    }

    public void obrisiKarton(int id) {
        if (kartoni.containsKey(id)) {
            kartoni.remove(id);
            sacuvajKartone();
        }
    }

    // Metoda ulogujSe ostaje ista
}
