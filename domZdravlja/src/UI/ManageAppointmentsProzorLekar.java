package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import domZdravlja.DatotekaManager;
import domZdravlja.Termin;
import domZdravlja.Status;
import domZdravlja.Lekar;

public class ManageAppointmentsProzorLekar extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;

    public ManageAppointmentsProzorLekar(JFrame parent) {
        setTitle("Upravljanje terminima - Lekar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Pacijent", "Datum", "Status", "Opis terapije"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tabela = new JTable(tableModel);
        osveziTabelu();

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Kreiraj termin");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                kreirajTermin();
            }
        });
        buttonPanel.add(createButton);

        JButton updateButton = new JButton("Ažuriraj termin");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                azurirajTermin();
            }
        });
        buttonPanel.add(updateButton);

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
            if (termin.getLekar().getId() == DatotekaManager.getUlogovanKorisnik().getId()) {
                tableModel.addRow(new Object[]{termin.getId(), termin.getPacijent().getIme(), termin.getDatum(), termin.getStatus(), termin.getOpisTerapije()});
            }
        }
    }

    private void kreirajTermin() {
        JTextField datumField = new JTextField();
        JTextField opisTerapijeField = new JTextField();

        Object[] inputFields = {
            "Datum:", datumField,
            "Opis terapije:", opisTerapijeField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Kreiraj termin", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String datum = datumField.getText();
            String opisTerapije = opisTerapijeField.getText();
            Termin noviTermin = new Termin(0, (Lekar) DatotekaManager.getUlogovanKorisnik(), null, datum, Status.SLOBODAN, opisTerapije);
            DatotekaManager.dodajTermin(noviTermin);
            osveziTabelu();
        }
    }

    private void azurirajTermin() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            int terminId = (int) tabela.getValueAt(selectedRow, 0);
            Termin termin = DatotekaManager.nadjiTerminPoId(terminId);
            if (termin.getStatus() == Status.SLOBODAN) {
                JTextField datumField = new JTextField(termin.getDatum());
                JTextField opisTerapijeField = new JTextField(termin.getOpisTerapije());

                Object[] inputFields = {
                    "Datum:", datumField,
                    "Opis terapije:", opisTerapijeField
                };

                int option = JOptionPane.showConfirmDialog(null, inputFields, "Ažuriraj termin", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    termin.setDatum(datumField.getText());
                    termin.setOpisTerapije(opisTerapijeField.getText());
                    DatotekaManager.azurirajTermin(terminId, termin);
                    osveziTabelu();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Samo slobodni termini mogu biti ažurirani.");
            }
        }
    }
}
