package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import domZdravlja.DatotekaManager;
import domZdravlja.Pacijent;
import domZdravlja.Termin;
import domZdravlja.ZdravstveniKarton;

public class ManageHealthRecordsProzor extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JFrame parentFrame;

    public ManageHealthRecordsProzor(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setTitle("Upravljaj zdravstvenim kartonima");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Pacijent", "Termini"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        osveziTabelu();

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dodajKarton();
            }
        });
        buttonPanel.add(addButton);

        JButton updateButton = new JButton("Ažuriraj");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                azurirajKarton();
            }
        });
        buttonPanel.add(updateButton);

        JButton deleteButton = new JButton("Obriši");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                obrisiKarton();
            }
        });
        buttonPanel.add(deleteButton);

        JButton backButton = new JButton("Nazad");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                parentFrame.setVisible(true);
            }
        });
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void dodajKarton() {
        JTextField pacijentField = new JTextField();
        JTextField terminiField = new JTextField();

        Object[] inputFields = {
            "Pacijent ID:", pacijentField,
            "Termini (ID-ovi odvojeni zarezom):", terminiField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Dodaj zdravstveni karton", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int pacijentId = Integer.parseInt(pacijentField.getText());
                String[] terminiIds = terminiField.getText().split(",");
                List<Termin> termini = DatotekaManager.nadjiTerminePoId(terminiIds);
                Pacijent pacijent = (Pacijent) DatotekaManager.nadjiKorisnikaPoId(pacijentId);

                if (pacijent != null) {
                    ZdravstveniKarton karton = new ZdravstveniKarton(0, pacijent);
                    karton.setTermini(termini);
                    DatotekaManager.dodajKarton(karton);
                    osveziTabelu();
                } else {
                    JOptionPane.showMessageDialog(null, "Pacijent nije pronađen.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nevalidan unos.");
            }
        }
    }

    private void azurirajKarton() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Izaberite karton za ažuriranje.");
            return;
        }

        int kartonId = (int) tableModel.getValueAt(selectedRow, 0);
        ZdravstveniKarton karton = DatotekaManager.nadjiKartonPoId(kartonId);

        if (karton == null) {
            JOptionPane.showMessageDialog(null, "Karton nije pronađen.");
            return;
        }

        JTextField pacijentField = new JTextField(String.valueOf(karton.getPacijent().getId()));
        JTextField terminiField = new JTextField(karton.getTerminiIds());

        Object[] inputFields = {
            "Pacijent ID:", pacijentField,
            "Termini (ID-ovi odvojeni zarezom):", terminiField
        };

        int option = JOptionPane.showConfirmDialog(null, inputFields, "Ažuriraj zdravstveni karton", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int pacijentId = Integer.parseInt(pacijentField.getText());
                String[] terminiIds = terminiField.getText().split(",");
                List<Termin> termini = DatotekaManager.nadjiTerminePoId(terminiIds);
                Pacijent pacijent = (Pacijent) DatotekaManager.nadjiKorisnikaPoId(pacijentId);

                if (pacijent != null) {
                    karton.setPacijent(pacijent);
                    karton.setTermini(termini);
                    DatotekaManager.azurirajKarton(kartonId, karton);
                    osveziTabelu();
                } else {
                    JOptionPane.showMessageDialog(null, "Pacijent nije pronađen.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Nevalidan unos.");
            }
        }
    }

    private void obrisiKarton() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Izaberite karton za brisanje.");
            return;
        }

        int kartonId = (int) tableModel.getValueAt(selectedRow, 0);
        int option = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da obrišete karton?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DatotekaManager.obrisiKarton(kartonId);
            osveziTabelu();
        }
    }

    private void osveziTabelu() {
        tableModel.setRowCount(0);
        List<ZdravstveniKarton> sviKartoni = DatotekaManager.sviKartoni();
        for (ZdravstveniKarton karton : sviKartoni) {
            Object[] rowData = {
                karton.getId(),
                karton.getPacijent().getKorisnickoIme(),
                karton.getTerminiIds()
            };
            tableModel.addRow(rowData);
        }
    }
}
