package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import domZdravlja.DatotekaManager;

public class GlavniProzorAdministrator extends JFrame {
    public GlavniProzorAdministrator() {
        setTitle("Glavni panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Dobro dosao korisnice" + " " + DatotekaManager.getUlogovanKorisnik().getKorisnickoIme() + " " + "vasa uloga je " + DatotekaManager.getUlogovanKorisnik().getUloga()));

        setVisible(true);
    }
}
