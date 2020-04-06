package fr.isen.m1.schedule.ejbs.ejbinterface;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.Remote;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Request;

@Remote
public interface AlgorithmInterface {
    public void ajouterRendezVous(Request nouvelleDemandeATraiter);


    public void checkHoraires();

    public void checkSiLaJourneeEstPleine();

    public List<Appointement> avoirLesRendezVousDejaDonnee(LocalDateTime dateDemander,
            Doctor docteurChoisit);

    public List<Appointement> rendezvousDejaDonneeAPartirDunMoment(LocalDateTime dateDemander,
            Doctor docteurChoisit);

    public List<Doctor> avoirLesDocteurDisponible();

    public LocalDateTime getDateEtHeure();

    public void renvoyeListeTrieeRendezVousStockage(List<Appointement> listeTriee);

}