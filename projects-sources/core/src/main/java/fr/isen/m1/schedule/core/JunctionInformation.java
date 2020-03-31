package fr.isen.m1.schedule.core;

import java.time.LocalDateTime;
import java.util.List;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;

/**
 * ReachInformation cette class sert a faire la junction entre lalgorithme et la
 * bdd (ici la simulation)
 */
public interface JunctionInformation {

    public void CreationDemandeDiagnostic(Diagnosis nouveauDiagnostic);

    public List<Appointement> getPrecedentRendezVous();

    public List<Appointement> getRendezVousDuJour(LocalDateTime jourChoisit, Doctor docteurChoisit);

    public List<Doctor> getDocteurDisponible();

    public Algorithme getAlgo();

    public void setAlgo(Algorithme algo);

    public Simulation getSimu();

    public void setSimu(Simulation simu);

    public LocalDateTime getDateTime();

    // recupere les rdv de la meme journee que ceux dans la liste en parametre
    // regarde s'il y a des changement ou des nouveaute si c'est le cas les applique
    // a la bdd
    public void comparaisonEtStockageRDV(List<Appointement> listeTriee);

    public Patient trouverLePatientAvecSonID(String patientID);

    public void recuperationNouveauxDiagnostic(int crit, String descript, String patientid);
}