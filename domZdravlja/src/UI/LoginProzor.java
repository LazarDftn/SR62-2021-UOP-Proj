package UI;

import javax.swing.*;

import domZdravlja.Osoba;
import domZdravlja.Termin;
import domZdravlja.ZdravstveniKarton;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class LoginProzor extends JFrame {
    private JTextField korisnickoImeField;
    private JPasswordField lozinkaField;
    private JButton prijavaButton;

    public LoginProzor(List<Osoba> korisnici, List<Termin> termini, List<ZdravstveniKarton> kartoni) {
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

    public LoginProzor() {
		// TODO Auto-generated constructor stub
	}

	private void prijava() {
        String korisnickoIme = korisnickoImeField.getText();
        String lozinka = new String(lozinkaField.getPassword());

        if (korisnickoIme.equals("admin")) {
            new GlavniProzorAdministrator();
        } else {
            JOptionPane.showMessageDialog(this, "Pogrešno korisničko ime ili lozinka!", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginProzor();
    }
}
