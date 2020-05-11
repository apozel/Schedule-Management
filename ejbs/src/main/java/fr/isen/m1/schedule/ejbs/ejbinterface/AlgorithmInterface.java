package fr.isen.m1.schedule.ejbs.ejbinterface;

import javax.ejb.Remote;
import fr.isen.m1.schedule.utilities.Request;

@Remote
public interface AlgorithmInterface {
        public String helloWord();

        public void addAppointementSchedule(Request newQuest);
}