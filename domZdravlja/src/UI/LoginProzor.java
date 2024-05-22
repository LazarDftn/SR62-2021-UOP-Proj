package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        String korisnickoIme = korisnickoImeField.getText();
        String lozinka = new String(lozinkaField.getPassword());

        // Ovde bi se dodala logika za proveru identiteta korisnika
        // Na primer, poziv metode koja proverava korisničko ime i lozinku

        // Simulacija: ako je korisničko ime "admin", otvara se prozor za administratora
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
