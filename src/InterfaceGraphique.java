import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * InterfaceGraphique
 */
public class InterfaceGraphique extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Simulation resteDuProjet = new Simulation();
    private optionJpanelDroite optionDroite = new optionJpanelDroite();
    private affichageMap map = new affichageMap();
    private Docteur docteurChoisit;

    public InterfaceGraphique() {
        // initialisation fenetre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(500, 500);
        this.setTitle("interface graphique");
        this.setFocusable(true);

        this.setVisible(true);

        // mise en forme fenetre

        this.setLayout(new BorderLayout());

        this.getContentPane().add(map, BorderLayout.CENTER);
        this.getContentPane().add(optionDroite, BorderLayout.EAST);

    }

    public static void main(String[] args) {
        InterfaceGraphique p = new InterfaceGraphique();
    }

    /**
     * affichageMap
     */
    public class affichageMap extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            // On le dessine de sorte qu'il occupe toute la surface
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
            resteDuProjet.montrerScheduleSelonDocteur(docteurChoisit, g2d);

        }

    }

    private class optionJpanelDroite extends JPanel implements ActionListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private JComboBox<Docteur> choixDocteurBox = new JComboBox<Docteur>();
        private JComboBox<Patient> creationNouvelleDemande = new JComboBox<Patient>();
        private JTextArea affichageDonnees = new JTextArea();
        private JButton optioButton = new JButton("bouton 1");
        private JTabbedPane tabbedPane = new JTabbedPane();
        private JPanel affichageJPanel = new JPanel();
        private JPanel ajoutJPanel = new JPanel();
        private JPanel ajoutDemandeJPanel = new JPanel();
        private JButton confirmationCreationDemandeButton = new JButton("confirmer");


        optionJpanelDroite() {

            BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            this.setLayout(boxlayout);

            BoxLayout affichageLayout = new BoxLayout(affichageJPanel, BoxLayout.Y_AXIS);
            affichageJPanel.setLayout(affichageLayout);

             BoxLayout ajoutLayout = new BoxLayout(ajoutJPanel, BoxLayout.Y_AXIS);
            ajoutJPanel.setLayout(ajoutLayout);


            for (Docteur var : resteDuProjet.getDoc()) {
                choixDocteurBox.addItem(var);
            }

            for (Patient Pati : resteDuProjet.getMalades()) {
                creationNouvelleDemande.addItem(Pati);
            }

            choixDocteurBox.addActionListener(this);

            tabbedPane.addTab("Affichage", affichageJPanel);
            tabbedPane.addTab("Ajout", ajoutJPanel);

            affichageJPanel.add(choixDocteurBox);
            affichageJPanel.add(affichageDonnees);
            affichageJPanel.add(optioButton);

            ajoutDemandeJPanel.add(creationNouvelleDemande);


            this.add(tabbedPane);
        }

        public void actionPerformed(ActionEvent e) {
            docteurChoisit = (Docteur) choixDocteurBox.getSelectedItem();
            affichageDonnees.setText(resteDuProjet.retourStringDesRDV(docteurChoisit));
        }
    }

}