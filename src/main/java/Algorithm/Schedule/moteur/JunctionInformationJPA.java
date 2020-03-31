package Algorithm.Schedule.moteur;

import java.time.LocalDateTime;
import java.util.List;

import Algorithm.Schedule.utilities.Diagnostic;
import Algorithm.Schedule.utilities.Docteur;
import Algorithm.Schedule.utilities.Patient;
import Algorithm.Schedule.utilities.RendezVous;

/**
 * JunctionInformationJson
 */
public class JunctionInformationJPA implements JunctionInformation {

    @Override
    public void CreationDemandeDiagnostic(Diagnostic nouveauDiagnostic) {
        // TODO Auto-generated method stub

    }

    @Override
    public void comparaisonEtStockageRDV(List<RendezVous> listeTriee) {
        // TODO Auto-generated method stub

    }

    @Override
    public Algorithme getAlgo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocalDateTime getDateTime() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Docteur> getDocteurDisponible() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RendezVous> getPrecedentRendezVous() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RendezVous> getRendezVousDuJour(LocalDateTime jourChoisit, Docteur docteurChoisit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Simulation getSimu() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void recuperationNouveauxDiagnostic(int crit, String descript, String patientid) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAlgo(Algorithme algo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setSimu(Simulation simu) {
        // TODO Auto-generated method stub

    }

    @Override
    public Patient trouverLePatientAvecSonID(String patientID) {
        // TODO Auto-generated method stub
        return null;
    }




}