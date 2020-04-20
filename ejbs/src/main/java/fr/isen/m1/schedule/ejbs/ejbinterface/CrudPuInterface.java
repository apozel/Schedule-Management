package fr.isen.m1.schedule.ejbs.ejbinterface;

import java.util.List;
import javax.ejb.Remote;
import fr.isen.m1.schedule.utilities.Doctor;

@Remote
public interface CrudPuInterface {

    public List<Doctor> findAllDoctor();

    public Long createDoctor(Doctor newDoc);

    public Doctor findDoctorById(Long id);
}
