package bigData.Schedule.moteur;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import bigData.Schedule.utilities.Demande;
import bigData.Schedule.utilities.Diagnostic;
import bigData.Schedule.utilities.Docteur;
import bigData.Schedule.utilities.Patient;
import bigData.Schedule.utilities.RendezVous;

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

    public void CreationDemandeDiagnostic(Diagnostic nouveauDiagnostic) {
        algo.ajouterRendezVous(new Demande(nouveauDiagnostic, nouveauDiagnostic.getPatientConserne()));
    }

    public List<RendezVous> getPrecedentRendezVous() {
        return simu.getListRDV();
    }

    public List<RendezVous> getRendezVousDuJour(LocalDateTime jourChoisit, Docteur docteurChoisit) {
        List<RendezVous> rdvDuJour = new ArrayList<RendezVous>();
        for (RendezVous var : this.getPrecedentRendezVous()) {
            if (var.getDate().equals( jourChoisit.toLocalDate())
                    
                    && var.getMedecinAffecte().equals(docteurChoisit)) {
                rdvDuJour.add(new RendezVous(var));
            }
        }
        return rdvDuJour;
    }

    public List<Docteur> getDocteurDisponible() {
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
    public void comparaisonEtStockageRDV(List<RendezVous> listeTriee) {
        for (RendezVous rendezVous : listeTriee) {
            this.simu.suppressRendezvousSelonID(rendezVous);
        }
        this.simu.addRdv(listeTriee);
        System.out.println("junctionInformation : comparaisonEtStockageRDV() : notif enregistrement");
    }

    public Patient trouverLePatientAvecSonID(String patientID) {
        return simu.retourPatientSelonID(patientID);
    }

    public void recuperationNouveauxDiagnostic(int crit, String descript, String patientid) {
        this.CreationDemandeDiagnostic(new Diagnostic(crit, descript, this.trouverLePatientAvecSonID(patientid)));
    }

}