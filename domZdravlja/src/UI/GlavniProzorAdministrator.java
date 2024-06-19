package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domZdravlja.DatotekaManager;

public class GlavniProzorAdministrator extends JFrame {
    public GlavniProzorAdministrator() {
        setTitle("Glavni panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel welcomeLabel = new JLabel("Dobro došli, " + DatotekaManager.getUlogovanKorisnik().getKorisnickoIme() + " (" + DatotekaManager.getUlogovanKorisnik().getUloga() + ")");
        add(welcomeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1));

        JButton manageUsersButton = new JButton("Upravljaj korisnicima");
        manageUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageUsersProzor(GlavniProzorAdministrator.this);
                setVisible(false);
            }
        });
        buttonPanel.add(manageUsersButton);

        JButton manageAppointmentsButton = new JButton("Upravljaj terminima");
        manageAppointmentsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageAppointmentsProzor(GlavniProzorAdministrator.this);
                setVisible(false);
            }
        });
        buttonPanel.add(manageAppointmentsButton);

        JButton manageHealthRecordsButton = new JButton("Upravljaj zdravstvenim kartonima");
        manageHealthRecordsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ManageHealthRecordsProzor(GlavniProzorAdministrator.this);
                setVisible(false);
            }
        });
        buttonPanel.add(manageHealthRecordsButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
