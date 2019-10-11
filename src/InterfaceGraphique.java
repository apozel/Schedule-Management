import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * InterfaceGraphique
 */
public class InterfaceGraphique extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Systeme resteDuProjet = new Systeme();
    private optionJpanelDroite optionDroite = new optionJpanelDroite();

    public InterfaceGraphique() {
        // initialisation fenetre 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setTitle("interface graphique");
        this.setFocusable(true);

        this.setVisible(true);
       )

        // mise en forme fenetre 

        this.setLayout(new BorderLayout());

        this.getContentPane().add(optionDroite,BorderLayout.EAST);

    }

    public static void main(String[] args) {
        new InterfaceGraphique();
    }

    /**
     * InnerInterfaceGraphique
     */
    public class InnerInterfaceGraphique extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            // On le dessine de sorte qu'il occupe toute la surface
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(this.getWidth()/2, this.getHeight()/2);
        }

    }

    private class optionJpanelDroite extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private JTextArea affichageDonnees = new JTextArea();
        private JButton optioButton = new JButton("bouton 1");

        optionJpanelDroite() {
            this.add(affichageDonnees);
            this.add(optioButton);
        }
    }

}