package domZdravlja;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatotekaManager {

	public List<Osoba> ucitajKorisnike() {
	    List<Osoba> korisnici = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(KORISNICI_FAJL))) {
	        String linija;
	        while ((linija = br.readLine()) != null) {
	            String[] podaci = linija.split(",");
	            Osoba osoba = kreirajOsobuIzPodataka(podaci);
	            if (osoba != null) {
	                korisnici.add(osoba);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Greška prilikom čitanja fajla: " + e.getMessage());
	    }
	    return korisnici;
	}

	public void sacuvajKorisnike(List<Osoba> korisnici) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(KORISNICI_FAJL))) {
	        for (Osoba osoba : korisnici) {
	            bw.write(osoba.toString() + "\n");
	        }
	    } catch (IOException e) {
	        System.out.println("Greška prilikom pisanja u fajl: " + e.getMessage());
	    }
	}
	private Osoba kreirajOsobuIzPodataka(String[] podaci) {
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

    private static final String KORISNICI_FAJL = "korisnici.txt";
    private static final String TERMINI_FAJL = "termini.txt";
    private static final String KARTONI_FAJL = "zdravstveni_kartoni.txt";
	public List<Termin> ucitajTermine() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ZdravstveniKarton> ucitajKartone() {
		// TODO Auto-generated method stub
		return null;
	}

}
