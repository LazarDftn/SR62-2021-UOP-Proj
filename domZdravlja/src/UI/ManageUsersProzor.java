package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domZdravlja.DatotekaManager;
import domZdravlja.Osoba;
import domZdravlja.Administrator;
import domZdravlja.Lekar;
import domZdravlja.Pacijent;
import domZdravlja.Pol;
import domZdravlja.Uloga;

public class ManageUsersProzor extends JFrame {
    public ManageUsersProzor() {
        setTitle("Upravljaj korisnicima");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1)); 

        JLabel label = new JLabel("Upravljanje korisnicima");
        add(label);

        JButton addUserButton = new JButton("Dodaj korisnika");
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dodajKorisnika();
            }
        });
        add(addUserButton);

        JButton updateUserButton = new JButton("Ažuriraj korisnika");
        updateUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                azurirajKorisnika();
            }
        });
        add(updateUserButton);

        JButton deleteUserButton = new JButton("Obriši korisnika");
        deleteUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obrisiKorisnika();
            }
        });
        add(deleteUserButton);

        setVisible(true);
    }

    private void dodajKorisnika() {

        JTextField imeField = new JTextField();
        JTextField prezimeField = new JTextField();
        JTextField jmbgField = new JTextField();
        JComboBox<Pol> polField = new JComboBox<>(Pol.values());
        JTextField adresaField = new JTextField();
        JTextField telefonField = new JTextField();
        JTextField korisnickoImeField = new JTextField();
        JTextField lozinkaField = new JTextField();
        JComboBox<Uloga> ulogaField = new JComboBox<>(Uloga.values());

        Object[] inputFields = {
            "Ime:", imeField,
            "Prezime:", prezimeField,
            "JMBG:", jmbgField,
            "Pol:", polField,
            "Adresa:", adresaField,
            "Telefon:", telefonField,
            "Korisničko ime:", korisnickoImeField,
            "Lozinka:", lozinkaField,
            "Uloga:", ulogaField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Dodaj korisnika", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {

            Osoba novaOsoba = null;
            String ime = imeField.getText();
            String prezime = prezimeField.getText();
            String jmbg = jmbgField.getText();
            Pol pol = (Pol) polField.getSelectedItem();
            String adresa = adresaField.getText();
            String telefon = telefonField.getText();
            String korisnickoIme = korisnickoImeField.getText();
            String lozinka = lozinkaField.getText();
            Uloga uloga = (Uloga) ulogaField.getSelectedItem();

            if (uloga == Uloga.ADMIN) {
                novaOsoba = new Administrator(0, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
            } else if (uloga == Uloga.PACIJENT) {
                novaOsoba = new Pacijent(0, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
            } else if (uloga == Uloga.LEKAR) {
                novaOsoba = new Lekar(0, ime, prezime, jmbg, pol, adresa, telefon, korisnickoIme, lozinka, uloga);
            }

            if (novaOsoba != null) {
                DatotekaManager.dodajKorisnika(novaOsoba);
                JOptionPane.showMessageDialog(null, "Korisnik uspešno dodat.");
            } else {
                JOptionPane.showMessageDialog(null, "Greška prilikom dodavanja korisnika.");
            }
        }
    }

    private void azurirajKorisnika() {
        String korisnickoIme = JOptionPane.showInputDialog("Unesite korisničko ime korisnika za ažuriranje:");
        Osoba korisnik = DatotekaManager.nadjiKorisnikaPoKorisnickomImenu(korisnickoIme);
        if (korisnik == null) {
            JOptionPane.showMessageDialog(null, "Korisnik nije pronađen.");
            return;
        }

        JTextField imeField = new JTextField(korisnik.getIme());
        JTextField prezimeField = new JTextField(korisnik.getPrezime());
        JTextField jmbgField = new JTextField(korisnik.getJmbg());
        JComboBox<Pol> polField = new JComboBox<>(Pol.values());
        polField.setSelectedItem(korisnik.getPol());
        JTextField adresaField = new JTextField(korisnik.getAdresa());
        JTextField telefonField = new JTextField(korisnik.getTelefon());
        JTextField korisnickoImeField = new JTextField(korisnik.getKorisnickoIme());
        JTextField lozinkaField = new JTextField(korisnik.getLozinka());
        JComboBox<Uloga> ulogaField = new JComboBox<>(Uloga.values());
        ulogaField.setSelectedItem(korisnik.getUloga());

        Object[] inputFields = {
            "Ime:", imeField,
            "Prezime:", prezimeField,
            "JMBG:", jmbgField,
            "Pol:", polField,
            "Adresa:", adresaField,
            "Telefon:", telefonField,
            "Korisničko ime:", korisnickoImeField,
            "Lozinka:", lozinkaField,
            "Uloga:", ulogaField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Ažuriraj korisnika", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            korisnik.setIme(imeField.getText());
            korisnik.setPrezime(prezimeField.getText());
            korisnik.setJmbg(jmbgField.getText());
            korisnik.setPol((Pol) polField.getSelectedItem());
            korisnik.setAdresa(adresaField.getText());
            korisnik.setTelefon(telefonField.getText());
            korisnik.setKorisnickoIme(korisnickoImeField.getText());
            korisnik.setLozinka(lozinkaField.getText());
            korisnik.setUloga((Uloga) ulogaField.getSelectedItem());

            DatotekaManager.azurirajKorisnika(korisnik.getId(), korisnik);
            JOptionPane.showMessageDialog(null, "Korisnik uspešno ažuriran.");
        }
    }

    private void obrisiKorisnika() {
        // Implementacija brisanja korisnika
        String korisnickoIme = JOptionPane.showInputDialog("Unesite korisničko ime korisnika za brisanje:");
        Osoba korisnik = DatotekaManager.nadjiKorisnikaPoKorisnickomImenu(korisnickoIme);
        if (korisnik == null) {
            JOptionPane.showMessageDialog(null, "Korisnik nije pronađen.");
            return;
        }

        int option = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete korisnika " + korisnickoIme + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DatotekaManager.obrisiKorisnika(korisnik.getId());
            JOptionPane.showMessageDialog(null, "Korisnik uspešno obrisan.");
        }
    }
}
