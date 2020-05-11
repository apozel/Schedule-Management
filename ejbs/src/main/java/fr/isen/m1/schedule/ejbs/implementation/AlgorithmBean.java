package fr.isen.m1.schedule.ejbs.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.hibernate.annotations.Check;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;
import fr.isen.m1.schedule.ejbs.ejbinterface.CrudPuInterface;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Request;

@Stateless(mappedName = "AlgorithmInterface")
public class AlgorithmBean implements AlgorithmInterface {

    private Doctor chooseDoctor;
    private LocalDateTime now = LocalDateTime.now();
    private List<Appointement> dayAppointements;

    @EJB(mappedName = "CrudPuInterface")
    private CrudPuInterface crud;

    @Override
    public String helloWord() {

        return "Hello World";
    }

    @Override
    public void addAppointementSchedule(Request newQuest) {
        List<Appointement> returnAppointements;


    }

    public Doctor chooseDoctor() {
        return crud.findAllDoctor().get(0);
    }

    public void checkHoraires() {

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
        return null;//crud.findAppointementByDayDoctor();
    }

}
