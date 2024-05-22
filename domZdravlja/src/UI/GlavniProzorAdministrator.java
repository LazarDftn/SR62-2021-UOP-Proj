package UI;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GlavniProzorAdministrator extends JFrame {
    public GlavniProzorAdministrator() {
        setTitle("Administratorski Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ovde bi se dodale komponente kao što su dugmad za pregled korisnika, termina itd.
        // Na primer:
        add(new JButton("Upravljanje korisnicima"));
        add(new JButton("Upravljanje terminima"));

        setVisible(true);
    }
}
