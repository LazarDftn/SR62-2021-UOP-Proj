package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domZdravlja.DatotekaManager;
import domZdravlja.Osoba;
import domZdravlja.Uloga;

public class LoginProzor extends JFrame {
    private JTextField korisnickoImeField;
    private JPasswordField lozinkaField;
    private JButton prijavaButton;

    public LoginProzor() {
        setTitle("Prijava");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Korisničko ime:"));
        korisnickoImeField = new JTextField(20);
        add(korisnickoImeField);

        add(new JLabel("Lozinka:"));
        lozinkaField = new JPasswordField(20);
        add(lozinkaField);

        prijavaButton = new JButton("Prijava");
        prijavaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prijava();
            }
        });
        add(prijavaButton);

        setVisible(true);
    }

    private void prijava() {
        boolean result = DatotekaManager.ulogujSe(korisnickoImeField.getText(), new String(lozinkaField.getPassword()));
        if (result) {
            Osoba ulogovanKorisnik = DatotekaManager.getUlogovanKorisnik();
            Uloga uloga = ulogovanKorisnik.getUloga();

            LoginProzor.this.dispose();
            LoginProzor.this.setVisible(false);

            if (uloga == Uloga.ADMIN) {
                new GlavniProzorAdministrator().setVisible(true);
            } else if (uloga == Uloga.LEKAR) {
                new GlavniProzorLekar().setVisible(true);
            } else if (uloga == Uloga.PACIJENT) {
                new GlavniProzorPacijent().setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pogrešno korisničko ime ili lozinka!", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        DatotekaManager.ucitaj();
        new LoginProzor();
    }
}
