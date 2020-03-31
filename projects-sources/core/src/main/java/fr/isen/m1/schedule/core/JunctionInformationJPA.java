package fr.isen.m1.schedule.core;

import java.time.LocalDateTime;
import java.util.List;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;

/**
 * JunctionInformationJson
 */
public class JunctionInformationJPA implements JunctionInformation {

    @Override
    public void CreationDemandeDiagnostic(Diagnosis nouveauDiagnostic) {
        // TODO Auto-generated method stub

    }

    @Override
    public void comparaisonEtStockageRDV(List<Appointement> listeTriee) {
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
    public List<Doctor> getDocteurDisponible() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Appointement> getPrecedentRendezVous() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Appointement> getRendezVousDuJour(LocalDateTime jourChoisit, Doctor docteurChoisit) {
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