package fr.isen.m1.schedule.ejbs.implementation;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import fr.isen.m1.schedule.ejbs.ejbinterface.CrudPuInterface;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;

@Stateless(mappedName = "CrudPuInterface")
public class CrudPuBean implements CrudPuInterface {

    @PersistenceContext(unitName = "schedulePU")
    EntityManager em;
    // private static Logger logger = Logger.getLogger(CrudPuBean.class);

    @Override
    public List<Doctor> findAllDoctor() {
        // logger.info("doctor find all");
        Query query = em.createNamedQuery("Doctor.findAll", Doctor.class);
        @SuppressWarnings("unchecked")
        List<Doctor> Doctors = query.getResultList();
        // logger.debug(Doctors);
        return Doctors;
    }

    @Override
    public Long createDoctor(Doctor newDoc) {
        // logger.info("create Doctor");
        em.persist(newDoc);
        // logger.debug("new doc : " + newDoc);
        // logger.debug("new doc id : " + newDoc.getId());
        return newDoc.getId();

    }

    @Override
    public Doctor findDoctorById(Long id) {
        return em.find(Doctor.class, id);
    }

    @Override
    public Doctor findDoctorByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void suppressDoctor(Doctor doctor) {
        em.remove(doctor);
    }

    @Override
    public List<Patient> findAllPatient() {
        // logger.info("doctor find all");
        Query query = em.createNamedQuery("Patient.findAll", Patient.class);
        @SuppressWarnings("unchecked")
        List<Patient> patients = query.getResultList();
        // logger.debug(patients);
        return patients;
    }

    @Override
    public Long createPatient(Patient newDoc) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Patient findPatientById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Patient findPatientByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void suppressPatientById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Appointement> findAllAppointement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long createAppointement(Appointement newDoc) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Appointement findAppointementById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void suppressAppointementById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Diagnosis> findAllDiagnosis() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Long createDiagnosis(Diagnosis newDoc) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Diagnosis findDiagnosisById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Diagnosis findDiagnosisByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void suppressDiagnosisById(Long id) {
        // TODO Auto-generated method stub

    }



}
