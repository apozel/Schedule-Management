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
        em.flush();
        return newDoc.getId();

    }

    @Override
    public Doctor findDoctorById(Long id) {
        return em.find(Doctor.class, id);
    }

    @Override
    public Doctor findDoctorByName(String lastName) {
        Query query = em.createNamedQuery("Doctor.findByName", Doctor.class).setParameter("name", lastName);
        return (Doctor) query.getSingleResult();
    }

    @Override
    public void suppressDoctor(Doctor doctor) throws Exception {
        em.remove(doctor);
        em.flush();
        em.clear();
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
    public Long createPatient(Patient newPatient) {
        em.persist(newPatient);
        em.flush();
        return newPatient.getId();
    }

    @Override
    public Patient findPatientById(Long id) {
        return em.find(Patient.class, id);
    }

    @Override
    public Patient findPatientByName(String lastName) {
        Query query = em.createQuery("patient.findByname", Patient.class).setParameter("name", lastName);
        return (Patient) query.getSingleResult();
    }

    @Override
    public void suppressPatient(Patient patientSuppress) {
        em.remove(patientSuppress);
    }

    @Override
    public List<Appointement> findAllAppointement() {
        Query query = em.createNamedQuery("Appointement.findAll", Appointement.class);
        @SuppressWarnings("unchecked")
        List<Appointement> appointements = query.getResultList();
        // logger.debug(appointements);
        return appointements;
    }

    @Override
    public Long createAppointement(Appointement newAppointement) {
        em.persist(newAppointement);
        em.flush();
        return newAppointement.getId();
    }

    @Override
    public Appointement findAppointementById(Long id) {
        return em.find(Appointement.class, id);
    }

    @Override
    public void suppressAppointement(Appointement suppressAppointement) {
        em.remove(suppressAppointement);
    }

    @Override
    public List<Appointement> findAppointementByDoctor(Doctor doctor) {
        Query query = em.createQuery("Appointement.findByDoctor", Appointement.class).setParameter("doc", doctor);

        return query.getResultList();
    }

    @Override
    public List<Diagnosis> findAllDiagnosis() {
        Query query = em.createNamedQuery("Diagnosis.findAll", Diagnosis.class);
        @SuppressWarnings("unchecked")
        List<Diagnosis> diagnosis = query.getResultList();
        // logger.debug(diagnosis);
        return diagnosis;
    }

    @Override
    public Long createDiagnosis(Diagnosis newDiagnosis) {
        em.persist(newDiagnosis);
        em.flush();
        return newDiagnosis.getId();
    }

    @Override
    public Diagnosis findDiagnosisById(Long id) {
        return em.find(Diagnosis.class, id);
    }

    @Override
    public Diagnosis findDiagnosisByName(String docName) {
        // TODO changer la fonction en recherche en fonction du docteur
        return null;
    }

    @Override
    public void suppressDiagnosis(Diagnosis suppressDiagnosis) {
        em.remove(suppressDiagnosis);
    }

}
