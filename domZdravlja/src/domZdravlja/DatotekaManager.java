package domZdravlja;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domZdravlja.*;




public class DatotekaManager {
	
	private static final String DATA_FOLDER = "fajlovi";
	private static final Path KORISNICI_PUTANJA = Paths.get(DATA_FOLDER, "korisnici.csv");
	private static final Map<Integer, Osoba> korisnici = new HashMap<>();
	
	private static Osoba ulogovanKorisnik = null;

	public static Osoba getUlogovanKorisnik() {
		return ulogovanKorisnik;
	}

	public static void setUlogovanKorisnik(Osoba ulogovanKorisnik) {
		DatotekaManager.ulogovanKorisnik = ulogovanKorisnik;
	}
	

	public static void ucitaj() {
		// bitan je redosled metoda
		try {
			ucitajKorisnike();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private static void  ucitajKorisnike() throws IOException {
		for (String linija : Files.readAllLines(KORISNICI_PUTANJA)) {
			String info[] = linija.split(",");

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

			if (uloga.compareTo(Uloga.ADMIN) == 0) {
				Administrator admin = new Administrator(ime, prezime,  jmbg,  pol,  adresa,  telefon,  korisnickoIme,  lozinka, uloga);
				korisnici.put(id, admin);
				// korisniciLista.add(admin);
			} else if (uloga.compareTo(Uloga.PACIJENT) == 0) {
				Pacijent pacijent = new Pacijent(ime, prezime,  jmbg,  pol,  adresa,  telefon,  korisnickoIme,  lozinka, uloga);
				korisnici.put(id, pacijent);
			} else if (uloga.compareTo(Uloga.LEKAR) == 0) {
				Lekar lekar = new Lekar(ime, prezime,  jmbg,  pol,  adresa,  telefon,  korisnickoIme,  lozinka, uloga);
				korisnici.put(id, lekar);
			}

		}
	}

/*	public void sacuvajKorisnike(List<Osoba> korisnici) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(KORISNICI_FAJL))) {
	        for (Osoba osoba : korisnici) {
	            bw.write(osoba.toString() + "\n");
	        }
	    } catch (IOException e) {
	        System.out.println("Greška prilikom pisanja u fajl: " + e.getMessage());
	    }
	}
/*	private Osoba kreirajOsobuIzPodataka(String[] podaci) {
	    if (podaci.length < 9) {
	        return null;
	    }
	    
	    try {
	        String ime = podaci[0];
	        String prezime = podaci[1];
	        String jmbg = podaci[2];
	        Pol pol = Pol.valueOf(podaci[3]);
	        String adresa = podaci[4];
	        String telefon = podaci[5];
	        String korisnickoIme = podaci[6];
	        String lozinka = podaci[7];
	        String uloga = podaci[8];

	        switch (uloga) {
	            case "Administrator":
	                return new Administrator(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka);
	            case "Lekar":
	                return new Lekar(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka);
	            case "Pacijent":
	                return new Pacijent(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, null);
	            default:
	               return null;
	        }
	    } catch (IllegalArgumentException e) {
	        System.out.println("Nevalidan ulaz za enum tip: " + e.getMessage());
	        return null;}
	}
*/
	public List<Termin> ucitajTermine() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ZdravstveniKarton> ucitajKartone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static boolean ulogujSe(String kIme, String lozinka) {
		// boolean ulogovan = false;
		for (Osoba osoba : korisnici.values()) {
			if (osoba.getKorisnickoIme().equals(kIme) && osoba.getLozinka().equals(lozinka)) {
				// ulogovan = true;
				ulogovanKorisnik = osoba;
				System.out.println("Tip korisnika " + osoba.getUloga());
				return true;
			}
		}
		return false;
		// return ulogovan;
	}

}
