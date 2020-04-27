package fr.isen.m1.schedule.ejbs.ejbinterface;

import java.util.List;
import javax.ejb.Remote;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;

@Remote
public interface CrudPuInterface {

    public List<Doctor> findAllDoctor();

    public Long createDoctor(Doctor newDoc);

    public Doctor findDoctorById(Long id);

    public Doctor findDoctorByName(String name);

    public void suppressDoctor(Doctor doctor) throws Exception;

    public List<Patient> findAllPatient();

    public Long createPatient(Patient newDoc);

    public Patient findPatientById(Long id);

    public Patient findPatientByName(String name);

    public void suppressPatient(Patient patientSuppress);

    public List<Appointement> findAllAppointement();

    public Long createAppointement(Appointement newDoc);

    public Appointement findAppointementById(Long id);

    public void suppressAppointement(Appointement suppressAppointement);

    public List<Appointement> findAppointementByDoctor(Doctor doctor);

    public List<Diagnosis> findAllDiagnosis();

    public Long createDiagnosis(Diagnosis newDoc);

    public Diagnosis findDiagnosisById(Long id);

    public Diagnosis findDiagnosisByName(String name);

    public void suppressDiagnosis(Diagnosis suppressDiagnosis);



}
