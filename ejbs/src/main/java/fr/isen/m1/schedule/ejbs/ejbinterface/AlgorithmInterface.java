package fr.isen.m1.schedule.ejbs.ejbinterface;

import javax.ejb.Remote;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Request;

@Remote
public interface AlgorithmInterface {
        public void addAppointementSchedule(Request newQuest);

        public void addAppointementSchedule(Request newQuest, Doctor chooseDoctor);

}
