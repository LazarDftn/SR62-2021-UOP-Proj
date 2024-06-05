package UI;

import javax.swing.*;
import java.awt.*;

public class ManageHealthRecordsProzor extends JFrame {
    public ManageHealthRecordsProzor() {
        setTitle("Upravljaj zdravstvenim kartonima");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Upravljanje zdravstvenim kartonima - funkcionalnost u razvoju.");
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
