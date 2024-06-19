package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import domZdravlja.DatotekaManager;
import domZdravlja.Termin;
import domZdravlja.Pacijent;
import domZdravlja.Status;

public class ManageAppointmentsProzorPacijent extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;

    public ManageAppointmentsProzorPacijent(JFrame parent) {
        setTitle("Upravljanje terminima - Pacijent");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Lekar", "Datum", "Status", "Opis terapije"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tabela = new JTable(tableModel);
        osveziTabelu();

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton scheduleButton = new JButton("Zakaži termin");
        scheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zakaziTermin();
            }
        });
        buttonPanel.add(scheduleButton);

        JButton cancelButton = new JButton("Otkaži termin");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                otkaziTermin();
            }
        });
        buttonPanel.add(cancelButton);

        JButton backButton = new JButton("Nazad");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void osveziTabelu() {
        tableModel.setRowCount(0);
        List<Termin> termini = DatotekaManager.sviTermini();
        for (Termin termin : termini) {
            if (termin.getPacijent().getId() == DatotekaManager.getUlogovanKorisnik().getId()) {
                tableModel.addRow(new Object[]{termin.getId(), termin.getLekar().getIme(), termin.getDatum(), termin.getStatus(), termin.getOpisTerapije()});
            }
        }
    }

    private void zakaziTermin() {
        JTextField datumField = new JTextField();
        JTextField opisTerapijeField = new JTextField();

        Object[] inputFields = {
            "Datum:", datumField,
            "Opis terapije:", opisTerapijeField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Zakaži termin", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String datum = datumField.getText();
            String opisTerapije = opisTerapijeField.getText();
            Termin noviTermin = new Termin(0, null, (Pacijent) DatotekaManager.getUlogovanKorisnik(), datum, Status.ZAKAZAN, opisTerapije);
            DatotekaManager.dodajTermin(noviTermin);
            osveziTabelu();
        }
    }

    private void otkaziTermin() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            int terminId = (int) tabela.getValueAt(selectedRow, 0);
            Termin termin = DatotekaManager.nadjiTerminPoId(terminId);
            if (termin.getStatus() == Status.ZAKAZAN) {
                DatotekaManager.otkaziTermin(terminId);
                osveziTabelu();
            } else {
                JOptionPane.showMessageDialog(this, "Samo zakazani termini mogu biti otkazani.");
            }
        }
    }
}
