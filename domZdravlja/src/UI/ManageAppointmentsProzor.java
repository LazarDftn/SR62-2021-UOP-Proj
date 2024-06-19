package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import domZdravlja.DatotekaManager;
import domZdravlja.Lekar;
import domZdravlja.Pacijent;
import domZdravlja.Status;
import domZdravlja.Termin;

public class ManageAppointmentsProzor extends JFrame {
    private JTable tabela;
    private DefaultTableModel tableModel;

    public ManageAppointmentsProzor(JFrame parent) {
        setTitle("Upravljanje terminima");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tabela = new JTable();
        osveziTabelu();
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Dodaj termin");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dodajTermin();
            }
        });
        buttonsPanel.add(addButton);

        JButton updateButton = new JButton("Ažuriraj termin");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                azurirajTermin();
            }
        });
        buttonsPanel.add(updateButton);

        JButton deleteButton = new JButton("Obriši termin");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obrisiTermin();
            }
        });
        buttonsPanel.add(deleteButton);

        JButton backButton = new JButton("Nazad");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.setVisible(true);
                dispose();
            }
        });
        buttonsPanel.add(backButton);

        add(buttonsPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void osveziTabelu() {
        String[] columnNames = {"ID", "Lekar", "Pacijent", "Datum", "Status", "Opis terapije"};
        tableModel = new DefaultTableModel(columnNames, 0);

        for (Termin termin : DatotekaManager.sviTermini()) {
            Object[] rowData = {
                termin.getId(),
                termin.getLekar().getIme() + " " + termin.getLekar().getPrezime(),
                termin.getPacijent().getIme() + " " + termin.getPacijent().getPrezime(),
                termin.getDatum(),
                termin.getStatus(),
                termin.getOpisTerapije()
            };
            tableModel.addRow(rowData);
        }
        tabela.setModel(tableModel);
    }

    private void dodajTermin() {
        JTextField lekarIdField = new JTextField();
        JTextField pacijentIdField = new JTextField();
        JTextField datumField = new JTextField();
        JComboBox<Status> statusField = new JComboBox<>(Status.values());
        JTextField opisTerapijeField = new JTextField();

        Object[] inputFields = {
            "ID lekara:", lekarIdField,
            "ID pacijenta:", pacijentIdField,
            "Datum (yyyy-MM-dd):", datumField,
            "Status:", statusField,
            "Opis terapije:", opisTerapijeField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Dodaj termin", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int lekarId = Integer.parseInt(lekarIdField.getText());
                int pacijentId = Integer.parseInt(pacijentIdField.getText());
                String datum = datumField.getText();
                Status status = (Status) statusField.getSelectedItem();
                String opisTerapije = opisTerapijeField.getText();

                Lekar lekar = (Lekar) DatotekaManager.nadjiKorisnikaPoId(lekarId);
                Pacijent pacijent = (Pacijent) DatotekaManager.nadjiKorisnikaPoId(pacijentId);

                if (lekar != null && pacijent != null) {
                    Termin noviTermin = new Termin(0, lekar, pacijent, datum, status, opisTerapije);
                    DatotekaManager.dodajTermin(noviTermin);
                    osveziTabelu();
                    JOptionPane.showMessageDialog(this, "Termin uspešno dodat.");
                } else {
                    JOptionPane.showMessageDialog(this, "Nevalidan ID lekara ili pacijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nevalidan unos za ID.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void azurirajTermin() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Morate odabrati termin iz tabele.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int terminId = (int) tableModel.getValueAt(selectedRow, 0);
        Termin termin = DatotekaManager.nadjiTerminPoId(terminId);

        JTextField lekarIdField = new JTextField(String.valueOf(termin.getLekar().getId()));
        JTextField pacijentIdField = new JTextField(String.valueOf(termin.getPacijent().getId()));
        JTextField datumField = new JTextField(termin.getDatum());
        JComboBox<Status> statusField = new JComboBox<>(Status.values());
        statusField.setSelectedItem(termin.getStatus());
        JTextField opisTerapijeField = new JTextField(termin.getOpisTerapije());

        Object[] inputFields = {
            "ID lekara:", lekarIdField,
            "ID pacijenta:", pacijentIdField,
            "Datum (yyyy-MM-dd):", datumField,
            "Status:", statusField,
            "Opis terapije:", opisTerapijeField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Ažuriraj termin", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int lekarId = Integer.parseInt(lekarIdField.getText());
                int pacijentId = Integer.parseInt(pacijentIdField.getText());
                String datum = datumField.getText();
                Status status = (Status) statusField.getSelectedItem();
                String opisTerapije = opisTerapijeField.getText();

                Lekar lekar = (Lekar) DatotekaManager.nadjiKorisnikaPoId(lekarId);
                Pacijent pacijent = (Pacijent) DatotekaManager.nadjiKorisnikaPoId(pacijentId);

                if (lekar != null && pacijent != null) {
                    Termin noviTermin = new Termin(termin.getId(), lekar, pacijent, datum, status, opisTerapije);
                    DatotekaManager.azurirajTermin(noviTermin.getId(), noviTermin);
                    osveziTabelu();
                    JOptionPane.showMessageDialog(this, "Termin uspešno ažuriran.");
                } else {
                    JOptionPane.showMessageDialog(this, "Nevalidan ID lekara ili pacijenta.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Nevalidan unos za ID.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void obrisiTermin() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Morate odabrati termin iz tabele.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int terminId = (int) tableModel.getValueAt(selectedRow, 0);

        int option = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da želite da obrišete termin?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DatotekaManager.obrisiTermin(terminId);
            osveziTabelu();
            JOptionPane.showMessageDialog(this, "Termin uspešno obrisan.");
        }
    }
}
