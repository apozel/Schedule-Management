package fr.isen.m1.schedule.ejbs.ejbinterface;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Remote;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.SocialDetails;

@Remote
public interface CrudPuInterface {

    public List<Doctor> findAllDoctor();

    public Doctor createDoctor(Doctor newDoc);

    public Doctor findDoctorById(Long id);

    public Doctor findDoctorByName(String lastName,String firstName);

    public void suppressDoctor(Doctor doctor) throws Exception;

    public List<Patient> findAllPatient();

    public Patient createPatient(Patient newDoc);

    public Patient findPatientById(Long id);

    public Patient findPatientByName(String name);

    public void suppressPatient(Patient patientSuppress);

    public List<Appointement> findAllAppointement();

    public Appointement createAppointement(Appointement newDoc);

    public Appointement findAppointementById(Long id);

    public void suppressAppointement(Appointement suppressAppointement);

    public List<Appointement> findAppointementByDoctor(Doctor doctor);

    public List<Diagnosis> findAllDiagnosis();

    public Diagnosis createDiagnosis(Diagnosis newDoc);

    public Diagnosis findDiagnosisById(Long id);

    public Diagnosis findDiagnosisByName(String name);

    public void suppressDiagnosis(Diagnosis suppressDiagnosis);

    public void suppressPosition(Position suppressPosition);

    public Position createPosition(Position newPosition);

    public Position findPositionById(Long id);

    public List<Appointement> findAppointementByDayDoctor(LocalDate day, Doctor doctor);

    public SocialDetails createSocialDetails(SocialDetails details);

}
