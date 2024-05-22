package domZdravlja;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatotekaManager {

    public List<Osoba> ucitajKorisnike(String putanja) {
        List<Osoba> korisnici = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(putanja))) {
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] podaci = linija.split(",");
                String ime = podaci[0];
                String prezime = podaci[1];
                String jmbg = podaci[2];
                Pol pol = Pol.valueOf(podaci[3]);
                String adresa = podaci[4];
                String telefon = podaci[5];
                String korisnickoIme = podaci[6];
                String lozinka = podaci[7];
                String uloga = podaci[8];

                Osoba osoba = null;
                if (uloga.equals("Administrator")) {
                    osoba = new Administrator(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka);
                } else if (uloga.equals("Lekar")) {
                    osoba = new Lekar(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka);
                } else if (uloga.equals("Pacijent")) {
                    osoba = new Pacijent(ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, null);
                }
                if (osoba != null) {
                    korisnici.add(osoba);
                }
            }
        } catch (IOException e) {
            System.out.println("Greška prilikom čitanja fajla: " + e.getMessage());
        }
        return korisnici;
    }
    public void sacuvajKorisnike(List<Osoba> korisnici, String putanja) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(putanja))) {
            for (Osoba osoba : korisnici) {
                String linija = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                    osoba.getIme(), osoba.getPrezime(), osoba.getJMBG(), osoba.getPol(),
                    osoba.getAdresa(), osoba.getTelefon(), osoba.getKorisnickoIme(),
                    osoba.getLozinka(), osoba.getUloga());
                bw.write(linija);
            }
        } catch (IOException e) {
            System.out.println("Greška prilikom pisanja u fajl: " + e.getMessage());
        }
    }

}
