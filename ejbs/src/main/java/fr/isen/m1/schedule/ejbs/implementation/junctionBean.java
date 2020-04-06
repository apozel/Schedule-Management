package fr.isen.m1.schedule.ejbs.implementation;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import fr.isen.m1.schedule.core.Algorithme;
import fr.isen.m1.schedule.core.Simulation;
import fr.isen.m1.schedule.ejbs.ejbinterface.JunctionInformationEJB;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;

/**
 * junctionEJB
 */
@Stateless(mappedName = "JunctionInformationEJB")
public class junctionBean implements JunctionInformationEJB {

    @PersistenceContext(unitName = "schedulePU")
    private EntityManager em;

    @Override
    public void CreationDemandeDiagnostic(Diagnosis nouveauDiagnostic) {
        // TODO Auto-generated method stub

    }

    @Override
    public void comparaisonEtStockageRDV(List<Appointement> listeTriee) {
        // TODO Auto-generated method stub

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
    public List<Appointement> getRendezVousDuJour(LocalDateTime jourChoisit,
            Doctor docteurChoisit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void recuperationNouveauxDiagnostic(int crit, String descript, String patientid) {
        // TODO Auto-generated method stub

    }

    @Override
    public Patient trouverLePatientAvecSonID(String patientID) {
        // TODO Auto-generated method stub
        return null;
    }



}