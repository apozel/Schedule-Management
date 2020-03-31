package Algorithm.Schedule.moteur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Algorithm.Schedule.utilities.Request;
import Algorithm.Schedule.utilities.Diagnosis;
import Algorithm.Schedule.utilities.Doctor;
import Algorithm.Schedule.utilities.Patient;
import Algorithm.Schedule.utilities.Appointement;

/**
 * ReachInformation cette class sert a faire la junction entre lalgorithme et la
 * bdd (ici la simulation)
 */
public class JunctionInformationSimulation implements JunctionInformation {

    private Algorithme algo = new Algorithme(this);
    private Simulation simu;

    public JunctionInformationSimulation(Simulation simu) {
        this.simu = simu;
    }

    public void CreationDemandeDiagnostic(Diagnosis nouveauDiagnostic) {
        algo.ajouterRendezVous(new Request(nouveauDiagnostic, nouveauDiagnostic.getPatientConserne()));
    }

    public List<Appointement> getPrecedentRendezVous() {
        return simu.getListRDV();
    }

    public List<Appointement> getRendezVousDuJour(LocalDateTime jourChoisit, Doctor docteurChoisit) {
        List<Appointement> rdvDuJour = new ArrayList<Appointement>();
        for (Appointement var : this.getPrecedentRendezVous()) {
            if (var.getDate().equals( jourChoisit.toLocalDate())

                    && var.getMedecinAffecte().equals(docteurChoisit)) {
                rdvDuJour.add(new Appointement(var));
            }
        }
        return rdvDuJour;
    }

    public List<Doctor> getDocteurDisponible() {
        return simu.getDoc();
    }

    public Algorithme getAlgo() {
        return algo;
    }

    public void setAlgo(Algorithme algo) {
        this.algo = algo;
    }

    public Simulation getSimu() {
        return simu;
    }

    public void setSimu(Simulation simu) {
        this.simu = simu;
    }

    public LocalDateTime getDateTime() {
        return simu.getDateHeureActuel();
    }

    // recupere les rdv de la meme journee que ceux dans la liste en parametre
    // regarde s'il y a des changement ou des nouveaute si c'est le cas les applique
    // a la bdd
    public void comparaisonEtStockageRDV(List<Appointement> listeTriee) {
        for (Appointement rendezVous : listeTriee) {
            this.simu.suppressRendezvousSelonID(rendezVous);
        }
        this.simu.addRdv(listeTriee);
        System.out.println("junctionInformation : comparaisonEtStockageRDV() : notif enregistrement");
    }

    public Patient trouverLePatientAvecSonID(String patientID) {
        return simu.retourPatientSelonID(patientID);
    }

    public void recuperationNouveauxDiagnostic(int crit, String descript, String patientid) {
        this.CreationDemandeDiagnostic(new Diagnosis(crit, descript, this.trouverLePatientAvecSonID(patientid)));
    }

}