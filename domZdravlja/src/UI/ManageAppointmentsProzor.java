package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import domZdravlja.DatotekaManager;
import domZdravlja.Termin;

public class ManageAppointmentsProzor extends JFrame {
    private DefaultListModel<Termin> appointmentListModel;

    public ManageAppointmentsProzor() {
        setTitle("Upravljaj terminima");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        appointmentListModel = new DefaultListModel<>();
        JList<Termin> appointmentList = new JList<>(appointmentListModel);
        JScrollPane scrollPane = new JScrollPane(appointmentList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Obriši termin");
        deleteButton.addActionListener(e -> deleteAppointment(appointmentList.getSelectedValue()));
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadAppointments();
        setVisible(true);
    }

    private void loadAppointments() {
        appointmentListModel.clear();
        for (Termin termin : DatotekaManager.getAllAppointments()) {
            appointmentListModel.addElement(termin);
        }
    }

    private void deleteAppointment(Termin termin) {
        if (termin != null) {
            DatotekaManager.obrisiTermin(termin.getId());
            loadAppointments();
        }
    }
}
