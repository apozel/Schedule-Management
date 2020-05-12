package fr.isen.m1.schedule.ejbs.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import fr.isen.m1.schedule.builder.AppointementBuilder;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;
import fr.isen.m1.schedule.ejbs.ejbinterface.CrudPuInterface;
import fr.isen.m1.schedule.marchant.moteur.MarchantDistanceCriticity;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Request;

@Stateless(mappedName = "AlgorithmInterface")
public class AlgorithmBean implements AlgorithmInterface {

    private Doctor chooseDoctor;
    private LocalDateTime now;
    private List<Appointement> dayAppointements;

    @EJB(mappedName = "CrudPuInterface")
    private CrudPuInterface crud;

    @Override
    public String helloWord() {

        return "Hello World";
    }

    @Override
    public void addAppointementSchedule(Request newQuest) {
        this.now = LocalDateTime.now();
        List<Appointement> returnAppointements = new ArrayList<Appointement>();
        this.chooseDoctor = chooseDoctor();
        Appointement appointementFromNewQuest = new AppointementBuilder().setDate(now.toLocalDate())
                .setDiagnosis(newQuest.getDiag()).setStartTime(now.toLocalTime()).build();
        appointementFromNewQuest = crud.createAppointement(appointementFromNewQuest);
        openHours();

        dayAppointements = avoirLesRendezVousDejaDonnee(now, chooseDoctor);
        checkSiLaJourneeEstPleine();
        dayAppointements.add(appointementFromNewQuest);
        System.out.println("appointement : " + dayAppointements);
        Diagnosis[] diagnosis = new Diagnosis[dayAppointements.size()];
        for (int i = 0; i < dayAppointements.size(); i++) {
            diagnosis[i] = dayAppointements.get(i).getDiag();
            System.out.println("diagnosis : " + diagnosis[I]);
        }
        System.out.println("diagnosis : " + diagnosis);
        System.out.println("diagnosis.length : " + diagnosis.length);
        if (diagnosis.length > 2) {
            MarchantDistanceCriticity calculAlgorithm =
                    new MarchantDistanceCriticity(diagnosis, chooseDoctor);
            diagnosis = calculAlgorithm.getListDiag();
        }
        openHours();
        System.out.println("heure avant : " + now);
        for (Diagnosis diagnosis2 : diagnosis) {
            Appointement appointementInOrder = crud.findAppointementByDiagnosis(diagnosis2);
            appointementInOrder.setDate(now.toLocalDate());
            appointementInOrder.setHeureDebut(now.toLocalTime());
            appointementInOrder.setMedecinAffecte(chooseDoctor);
            returnAppointements.add(appointementInOrder);
            this.now = now.plus(appointementInOrder.getDureeConsultation());
            openHours();
            appointementInOrder = crud.updateAppointement(appointementInOrder);
        }

    }

    public Doctor chooseDoctor() {
        return crud.findAllDoctor().get(0);
    }

    public void openHours() {

        if (this.now.toLocalTime().isBefore(this.chooseDoctor.getHoraires(0))) {
            this.now = LocalDateTime.of(now.toLocalDate(), this.chooseDoctor.getHoraires(0));
        } else if (this.now.toLocalTime().isAfter(this.chooseDoctor.getHoraires(1))
                && this.now.toLocalTime().isBefore(this.chooseDoctor.getHoraires(2))) {
            this.now = LocalDateTime.of(now.toLocalDate(), this.chooseDoctor.getHoraires(2));
        } else if (this.now.toLocalTime().isAfter(this.chooseDoctor.getHoraires(3))) {
            this.now = LocalDateTime.of(now.toLocalDate().plusDays(1),
                    this.chooseDoctor.getHoraires(0));
        }

    }

    public List<Appointement> avoirLesRendezVousDejaDonnee(LocalDateTime dateDemander,
            Doctor docteurChoisit) {
        return crud.findAppointementByDayDoctor(dateDemander.toLocalDate(), docteurChoisit);
    }

    public void checkSiLaJourneeEstPleine() {
        for (Appointement rendezVous : dayAppointements) {
            if (rendezVous.getHeureDebut().plus(rendezVous.getDureeConsultation())
                    .isAfter(this.chooseDoctor.getHoraires(3))) {
                this.now = LocalDateTime.of(now.toLocalDate().plusDays(1),
                        this.chooseDoctor.getHoraires(0));
                this.dayAppointements = avoirLesRendezVousDejaDonnee(now, chooseDoctor);
                checkSiLaJourneeEstPleine();
            }
        }
    }

}
