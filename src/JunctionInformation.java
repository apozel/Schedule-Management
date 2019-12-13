
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ReachInformation cette class sert a faire la junction entre lalgorithme et la
 * bdd (ici la simulation)
 */
public class JunctionInformation {

    private Algorithme algo = new Algorithme(this);
    private Simulation simu;

    public JunctionInformation(Simulation simu) {
        this.simu = simu;
    }

    public void CreationDemandeDiagnostic(Diagnostic nouveauDiagnostic) {
        algo.ajouterRendezVous(new Demande(nouveauDiagnostic, nouveauDiagnostic.getPatientConserne()));
    }

    public List<RendezVous> getPrecedentRendezVous() {
        return simu.getListRDV();
    }

    public List<RendezVous> getRendezVousDuJour(LocalDate jourChoisit,Docteur docteurChoisit) {
        List<RendezVous> rdvDuJour = new ArrayList<RendezVous>();
        for (RendezVous var : this.getPrecedentRendezVous()) {
            if (var.getDate() == jourChoisit && var.getMedecinAffecte().equals(docteurChoisit)) {
                rdvDuJour.add(var);
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
    // regarde s'il y a des cahgement ou des nouveaute si c'est le cas les applique
    // a la bdd
    public void comparaisonEtStockageRDV(List<RendezVous> listeTriee) {
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