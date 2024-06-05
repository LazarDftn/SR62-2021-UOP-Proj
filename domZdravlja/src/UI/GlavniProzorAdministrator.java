package UI;

import javax.swing.*;
import java.awt.*;
import domZdravlja.DatotekaManager;

public class GlavniProzorAdministrator extends JFrame {
    public GlavniProzorAdministrator() {
        setTitle("Glavni panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1)); 

        JLabel welcomeLabel = new JLabel("Dobro došao korisniče " + DatotekaManager.getUlogovanKorisnik().getKorisnickoIme() + ", vaša uloga je " + DatotekaManager.getUlogovanKorisnik().getUloga());
        add(welcomeLabel);

    
        JButton manageUsersButton = new JButton("Upravljaj korisnicima");
        manageUsersButton.addActionListener(e -> manageUsers());
        add(manageUsersButton);

    
        JButton manageAppointmentsButton = new JButton("Upravljaj terminima");
        manageAppointmentsButton.addActionListener(e -> manageAppointments());
        add(manageAppointmentsButton);

        JButton manageHealthRecordsButton = new JButton("Upravljaj zdravstvenim kartonima");
        manageHealthRecordsButton.addActionListener(e -> manageHealthRecords());
        add(manageHealthRecordsButton);

        setVisible(true);
    }

    private void manageUsers() {
        new ManageUsersProzor();
    }

    private void manageAppointments() {

        new ManageAppointmentsProzor();
    }

    private void manageHealthRecords() {
   
        new ManageHealthRecordsProzor();
    }
}
