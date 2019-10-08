import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * InterfaceGraphique
 */
public class InterfaceGraphique extends JFrame {

    private Systeme resteDuProjet = new Systeme();

    public InterfaceGraphique() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setTitle("interface graphique");
        this.setFocusable(true);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        new InterfaceGraphique();
    }

}