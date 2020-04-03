package fr.isen.m1.schedule.ejbs.ejbinterface;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Remote;
import fr.isen.m1.schedule.core.Algorithme;
import fr.isen.m1.schedule.core.JunctionInformation;
import fr.isen.m1.schedule.core.Simulation;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;

@Remote
public interface JunctionInformationEJB extends JunctionInformation {

    @Override
    void CreationDemandeDiagnostic(Diagnosis nouveauDiagnostic);

    @Override
    void comparaisonEtStockageRDV(List<Appointement> listeTriee);

    @Override
    Algorithme getAlgo();

    @Override
    LocalDateTime getDateTime();

    @Override
    List<Doctor> getDocteurDisponible();

    @Override
    List<Appointement> getPrecedentRendezVous();

    @Override
    List<Appointement> getRendezVousDuJour(LocalDateTime jourChoisit, Doctor docteurChoisit);

    @Override
    Simulation getSimu();

    @Override
    void recuperationNouveauxDiagnostic(int crit, String descript, String patientid);

    @Override
    void setAlgo(Algorithme algo);

    @Override
    void setSimu(Simulation simu);

    @Override
    Patient trouverLePatientAvecSonID(String patientID);


}
