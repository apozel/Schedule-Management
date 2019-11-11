import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
        this.setSize(800, 500);
        this.setTitle("interface graphique");
        this.setFocusable(true);

        this.setVisible(true);

        // mise en forme fenetre

        this.setLayout(new BorderLayout());

        this.getContentPane().add(map, BorderLayout.CENTER);
        this.getContentPane().add(optionDroite, BorderLayout.EAST);

    }

    public static void main(String[] args) {
        new InterfaceGraphique();

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
            System.out.println("par ici");
            g.setColor(Color.BLACK);
            // On le dessine de sorte qu'il occupe toute la surface
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(this.getWidth() / 2, this.getHeight() / 2);
            
            resteDuProjet.montrerScheduleSelonDocteur(docteurChoisit, g2d);

        }
        public void MAJdeLaMap(){
            this.repaint();
        }

    }

    private class optionJpanelDroite extends JPanel implements ActionListener {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private int criticiteMax = 5;
        private JComboBox<Docteur> choixDocteurBox = new JComboBox<Docteur>();
        private JComboBox<Patient> creationNouvelleDemandePatientComboBox = new JComboBox<Patient>();
        private JTextArea affichageDonnees = new JTextArea();
        private JButton optioButton = new JButton("bouton 1");
        private JTabbedPane tabbedPane = new JTabbedPane();
        private JPanel affichageJPanel = new JPanel();
        private LocalDate jourChoisit = LocalDate.now();
        private JTextField affichageDateJLabel = new JTextField();
        private JButton affichageDateJButton = new JButton("Date");
        private JPanel affichageDateJPanel = new JPanel();
        private JPanel ajoutJPanel = new JPanel();
        private JPanel ajoutDemandeJPanel = new JPanel();
        private JLabel ajoutCriticiteDemandLabel = new JLabel("Criticite :");
        private Choice ajoutCriticiteDemande = new Choice();
        private JLabel ajoutDescriptionJLabel = new JLabel("Description :");
        private JTextField ajoutDescriptionJTexteField = new JTextField();
        private JButton confirmationCreationDemandeButton = new JButton("confirmer");
        private JPanel ajoutPatientJPanel = new JPanel();
        private JLabel ajoutPatientNomJLabel = new JLabel("nom :");
        private JTextField ajoutPatientNomJTextField = new JTextField();
        private JLabel ajoutPatientPrenomJLabel = new JLabel("prenom :");
        private JTextField ajoutPatientPrenomJTextField = new JTextField();
        private JLabel ajoutPatientPositionJLabel = new JLabel("position :");
        private JTextField ajoutPatientPositionJTextField = new JTextField();
        private JButton confirmationCreationPatientJButton = new JButton("confirmer");

        optionJpanelDroite() {
            creerInterfaceGraphique();
        }

        public void creerInterfaceGraphique() {
            BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
            this.setLayout(boxlayout);

            BoxLayout affichageLayout = new BoxLayout(affichageJPanel, BoxLayout.Y_AXIS);
            affichageJPanel.setLayout(affichageLayout);

            BoxLayout ajoutLayout = new BoxLayout(ajoutJPanel, BoxLayout.Y_AXIS);
            ajoutJPanel.setLayout(ajoutLayout);

            BoxLayout ajoutLayoutDemande = new BoxLayout(ajoutDemandeJPanel, Y_AXIS);
            ajoutDemandeJPanel.setLayout(ajoutLayoutDemande);

            BoxLayout ajoutLayoutPatient = new BoxLayout(ajoutPatientJPanel, Y_AXIS);
            ajoutPatientJPanel.setLayout(ajoutLayoutPatient);

            BoxLayout affichageDateLayout = new BoxLayout(affichageDateJPanel, X_AXIS);
            affichageDateJPanel.setLayout(affichageDateLayout);

            MAJdesBarres();

            for (int i = 0; i < criticiteMax; i++) {
                ajoutCriticiteDemande.add(Integer.toString(i));
            }

            choixDocteurBox.addActionListener(this);

            tabbedPane.addTab("Affichage", affichageJPanel);
            tabbedPane.addTab("Ajout", ajoutJPanel);

            affichageDateJPanel.add(affichageDateJLabel);
            affichageDateJPanel.add(affichageDateJButton);

            affichageJPanel.add(choixDocteurBox);
            affichageJPanel.add(affichageDateJPanel);
            affichageJPanel.add(affichageDonnees);
            affichageJPanel.add(optioButton);
            optioButton.addActionListener(this);

            ajoutJPanel.add(ajoutDemandeJPanel);
            ajoutJPanel.add(ajoutPatientJPanel);

            ajoutDemandeJPanel.add(creationNouvelleDemandePatientComboBox);
            ajoutDemandeJPanel.add(ajoutCriticiteDemandLabel);
            ajoutDemandeJPanel.add(ajoutCriticiteDemande);
            ajoutDemandeJPanel.add(ajoutDescriptionJLabel);
            ajoutDemandeJPanel.add(ajoutDescriptionJTexteField);
            ajoutDemandeJPanel.add(confirmationCreationDemandeButton);
            confirmationCreationDemandeButton.addActionListener(this);

            ajoutPatientJPanel.add(ajoutPatientNomJLabel);
            ajoutPatientJPanel.add(ajoutPatientNomJTextField);
            ajoutPatientJPanel.add(ajoutPatientPrenomJLabel);
            ajoutPatientJPanel.add(ajoutPatientPrenomJTextField);
            ajoutPatientJPanel.add(ajoutPatientPositionJLabel);
            ajoutPatientJPanel.add(ajoutPatientPositionJTextField);
            ajoutPatientJPanel.add(confirmationCreationPatientJButton);
            confirmationCreationPatientJButton.addActionListener(this);

            this.add(tabbedPane);

        }

        public void MAJdesBarres() {

            choixDocteurBox.removeAllItems();
            creationNouvelleDemandePatientComboBox.removeAllItems();

            affichageDateJLabel.setText(jourChoisit.toString());

            for (Docteur var : resteDuProjet.getDoc()) {
                choixDocteurBox.addItem(var);
            }

            for (Patient Pati : resteDuProjet.getMalades()) {
                creationNouvelleDemandePatientComboBox.addItem(Pati);
            }

           
        }

        public void recuperationPourDemande() {
            resteDuProjet.nouveauDiagnostic(Integer.parseInt(ajoutCriticiteDemande.getSelectedItem()),
                    ajoutDescriptionJTexteField.getText(),
                    ((Patient) creationNouvelleDemandePatientComboBox.getSelectedItem()).getIDPatient());
            ajoutCriticiteDemande.select(0);
            ajoutDescriptionJTexteField.setText("");
            MAJdesBarres();
        }

        public void recuperationPourPatient() {
            if (ajoutPatientNomJTextField.getText() != "" && ajoutPatientPrenomJTextField.getText() != "") {
                int positionCarre = Integer.parseInt(ajoutPatientPositionJTextField.getText());
                Position prochainePositionPatient = new Position(positionCarre, positionCarre);
                resteDuProjet.addPatient(new Patient(prochainePositionPatient, ajoutPatientNomJTextField.getText(),
                        ajoutPatientPrenomJTextField.getText()));
            }
            ajoutPatientNomJTextField.setText("");
            ajoutPatientPrenomJTextField.setText("");
            ajoutPatientPositionJTextField.setText("");
            MAJdesBarres();
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == confirmationCreationDemandeButton) {
                recuperationPourDemande();
            } else if (e.getSource() == choixDocteurBox || e.getSource() == optioButton) {
                docteurChoisit = (Docteur) choixDocteurBox.getSelectedItem();
                affichageDonnees.setText(resteDuProjet.retourStringDesRDV(docteurChoisit));
                System.out.println(resteDuProjet.retourStringDesRDV(docteurChoisit));
            } else if (e.getSource() == confirmationCreationPatientJButton) {
                recuperationPourPatient();
            }

        }
    }

}