package fr.isen.m1.schedule.ejbs.implementation;

import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.SocialDetails;

@Stateless

public class CrudPuBean {

    @PersistenceContext(unitName = "schedulePU")
    EntityManager em;
    // private static Logger logger = Logger.getLogger(CrudPuBean.class);

    public List<Doctor> findAllDoctor() {
        // logger.info("doctor find all");
        Query query = em.createNamedQuery("Doctor.findAll", Doctor.class);
        @SuppressWarnings("unchecked")
        List<Doctor> Doctors = query.getResultList();
        // logger.debug(Doctors);
        return Doctors;
    }

    public Doctor createDoctor(Doctor newDoc) {
        // logger.info("create Doctor");
        em.persist(newDoc);
        // logger.debug("new doc : " + newDoc);
        // logger.debug("new doc id : " + newDoc.getId());
        em.flush();
        return newDoc;

    }

    public Doctor findDoctorById(Long id) {
        return em.find(Doctor.class, id);
    }

    public Doctor findDoctorByName(String lastName, String firstName) {
        Query query = em.createNamedQuery("Doctor.findByName", Doctor.class).setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
        Doctor doctor;
        try {
            doctor = (Doctor) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            doctor = null;
        }
        return doctor;
    }

    public void suppressDoctor(Doctor doctor) {
        if (!em.contains(doctor)) {
            doctor = em.merge(doctor);
        }
        em.remove(doctor);
        em.flush();
    }

    public List<Patient> findAllPatient() {
        // logger.info("doctor find all");
        Query query = em.createNamedQuery("Patient.findAll", Patient.class);
        @SuppressWarnings("unchecked")
        List<Patient> patients = query.getResultList();
        // logger.debug(patients);
        return patients;
    }

    public Patient createPatient(Patient newPatient) {
        em.persist(newPatient);
        em.flush();
        return newPatient;
    }

    public Patient findPatientById(Long id) {
        return em.find(Patient.class, id);
    }

    public Patient findPatientByName(String lastName, String firstName) {
        System.out.println("patient first name : " + firstName + " patient last name : " + lastName);
        Query query = em.createNamedQuery("Patient.findByName", Patient.class).setParameter("lastname", lastName).setParameter("firstname", firstName);
        try {
            return (Patient) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void suppressPatient(Patient patientSuppress) {
        if (!em.contains(patientSuppress)) {
            patientSuppress = em.merge(patientSuppress);
        }
        em.remove(patientSuppress);
        em.flush();
    }

    public List<Appointement> findAllAppointement() {
        Query query = em.createNamedQuery("Appointement.findAll", Appointement.class);
        @SuppressWarnings("unchecked")
        List<Appointement> appointements = query.getResultList();
        // logger.debug(appointements);
        return appointements;
    }

    public Appointement createAppointement(Appointement newAppointement) {
        em.persist(newAppointement);
        em.flush();
        return newAppointement;
    }

    public Appointement findAppointementById(Long id) {
        return em.find(Appointement.class, id);
    }

    public void suppressAppointement(Appointement suppressAppointement) {
        if (!em.contains(suppressAppointement)) {
            suppressAppointement = em.merge(suppressAppointement);
        }
        em.remove(suppressAppointement);
        em.flush();
    }

    public List<Appointement> findAppointementByDoctor(Doctor doctor) {
        Query query = em.createNamedQuery("Appointement.findByDoctor", Appointement.class).setParameter("doc", doctor);
        @SuppressWarnings("unchecked")
        List<Appointement> appointements = query.getResultList();
        return appointements;
    }

    public List<Diagnosis> findAllDiagnosis() {
        Query query = em.createNamedQuery("Diagnosis.findAll", Diagnosis.class);
        @SuppressWarnings("unchecked")
        List<Diagnosis> diagnosis = query.getResultList();
        // logger.debug(diagnosis);
        return diagnosis;
    }

    public Diagnosis createDiagnosis(Diagnosis newDiagnosis) {
        em.persist(newDiagnosis);
        em.flush();
        return newDiagnosis;
    }

    public Diagnosis findDiagnosisById(Long id) {
        return em.find(Diagnosis.class, id);
    }

    public Diagnosis findDiagnosisByName(String docName) {
        // TODO changer la fonction en recherche en fonction du docteur
        return null;
    }

    public void suppressDiagnosis(Diagnosis suppressDiagnosis) {
        if (!em.contains(suppressDiagnosis)) {
            suppressDiagnosis = em.merge(suppressDiagnosis);
        }
        em.remove(suppressDiagnosis);
        em.flush();
    }

    public void suppressPosition(Position suppressPosition) {

        if (!em.contains(suppressPosition)) {
            suppressPosition = em.merge(suppressPosition);
        }
        em.remove(suppressPosition);
        em.flush();

    }

    public Position createPosition(Position newPosition) {
        em.persist(newPosition);
        em.flush();
        return newPosition;
    }

    public Position findPositionById(Long id) {
        return em.find(Position.class, id);
    }

    public void modifyPosition(Position position) {
        em.merge(position);
        em.flush();
    }

    public List<Position> findAllPosition() {
        Query query = em.createNamedQuery("Position.findAll", Position.class);
        @SuppressWarnings("unchecked")
        List<Position> positions = query.getResultList();
        return positions;
    }

    public List<Appointement> findAppointementByDayDoctor(LocalDate day, Doctor doctor) {
        Query query = em.createNamedQuery("Appointement.findByDayDoctor", Appointement.class)
                .setParameter("doctor", doctor).setParameter("day", day);
        @SuppressWarnings("unchecked")
        List<Appointement> appointements = query.getResultList();
        // logger.debug(appointements);
        return appointements;
    }

    public SocialDetails createSocialDetails(SocialDetails details) {
        em.persist(details);
        em.flush();
        return details;
    }

    public Appointement findAppointementByDiagnosis(Diagnosis diagnosis) {
        Query query = em.createNamedQuery("Appointement.findByDiagnosis", Appointement.class).setParameter("diagnosis",
                diagnosis);
                try {
                    Appointement appointement = (Appointement) query.getSingleResult();
                    return appointement;
                } catch (Exception e) {
                    return null;
                }
    }

    public Appointement updateAppointement(Appointement appointement) {
        return em.merge(appointement);
    }

}
