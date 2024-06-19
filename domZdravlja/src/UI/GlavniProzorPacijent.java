package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domZdravlja.DatotekaManager;

public class GlavniProzorPacijent extends JFrame {
    public GlavniProzorPacijent() {
        setTitle("Glavni panel - Pacijent");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("Dobro došao, " + DatotekaManager.getUlogovanKorisnik().getIme() + " " + DatotekaManager.getUlogovanKorisnik().getPrezime() + ". Vaša uloga je Pacijent."));

        JButton manageAppointmentsButton = new JButton("Pregledaj termine");
        manageAppointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageAppointmentsProzor(GlavniProzorPacijent.this);
                setVisible(false);
            }
        });
        add(manageAppointmentsButton, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Izloguj se");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoginProzor();
                dispose();
            }
        });
        add(logoutButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
