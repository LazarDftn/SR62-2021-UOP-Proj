package UI;

import java.util.List;

import javax.swing.JOptionPane;

import domZdravlja.Osoba;
import domZdravlja.Termin;
import domZdravlja.ZdravstveniKarton;
import domZdravlja.DatotekaManager;

public class Main {
    public static void main(String[] args) {
        // Kreiranje instance klase koja upravlja podacima
    	DatotekaManager dataManager = new DatotekaManager();

        // Učitavanje podataka
        try {
            List<Osoba> korisnici = dataManager.ucitajKorisnike();
            List<Termin> termini = dataManager.ucitajTermine();
            List<ZdravstveniKarton> kartoni = dataManager.ucitajKartone();

            // Provera učitanih podataka
            if (korisnici.isEmpty() || termini.isEmpty() || kartoni.isEmpty()) {
                throw new IllegalStateException("Neki od neophodnih podataka nisu učitani. Molimo proverite fajlove.");
            }

            // Pokretanje korisničkog interfejsa
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    LoginProzor loginProzor = new LoginProzor(korisnici, termini, kartoni);
                    loginProzor.setVisible(true);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Došlo je do greške pri pokretanju aplikacije: " + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
