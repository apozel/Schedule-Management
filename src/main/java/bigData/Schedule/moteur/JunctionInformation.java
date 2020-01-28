package Schedule.moteur;

import java.time.LocalDateTime;
import java.util.List;

import Schedule.utilities.Diagnostic;
import Schedule.utilities.Docteur;
import Schedule.utilities.Patient;
import Schedule.utilities.RendezVous;

/**
 * ReachInformation cette class sert a faire la junction entre lalgorithme et la
 * bdd (ici la simulation)
 */
public interface JunctionInformation {

    public void CreationDemandeDiagnostic(Diagnostic nouveauDiagnostic);

    public List<RendezVous> getPrecedentRendezVous();

    public List<RendezVous> getRendezVousDuJour(LocalDateTime jourChoisit, Docteur docteurChoisit);

    public List<Docteur> getDocteurDisponible();

    public Algorithme getAlgo();

    public void setAlgo(Algorithme algo);

    public Simulation getSimu();

    public void setSimu(Simulation simu);

    public LocalDateTime getDateTime();

    // recupere les rdv de la meme journee que ceux dans la liste en parametre
    // regarde s'il y a des changement ou des nouveaute si c'est le cas les applique
    // a la bdd
    public void comparaisonEtStockageRDV(List<RendezVous> listeTriee);

    public Patient trouverLePatientAvecSonID(String patientID);

    public void recuperationNouveauxDiagnostic(int crit, String descript, String patientid);
}