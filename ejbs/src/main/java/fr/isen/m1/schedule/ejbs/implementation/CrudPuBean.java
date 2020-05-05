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
import fr.isen.m1.schedule.utilities.Position;

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
    public Doctor createDoctor(Doctor newDoc) {
        // logger.info("create Doctor");
        em.persist(newDoc);
        // logger.debug("new doc : " + newDoc);
        // logger.debug("new doc id : " + newDoc.getId());
        em.flush();
        return newDoc;

    }

    @Override
    public Doctor findDoctorById(Long id) {
        return em.find(Doctor.class, id);
    }

    @Override
    public Doctor findDoctorByName(String lastName) {
        Query query = em.createNamedQuery("Doctor.findByName", Doctor.class).setParameter("name",
                lastName);
        return (Doctor) query.getResultList().get(0);
    }

    @Override
    public void suppressDoctor(Doctor doctor) throws Exception {
        if (!em.contains(doctor)) {
            doctor = em.merge(doctor);
        }
        em.remove(doctor);
        em.flush();
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
    public Patient createPatient(Patient newPatient) {
        em.persist(newPatient);
        em.flush();
        return newPatient;
    }

    @Override
    public Patient findPatientById(Long id) {
        return em.find(Patient.class, id);
    }

    @Override
    public Patient findPatientByName(String lastName) {
        Query query =
                em.createNamedQuery("Patient.findByName", Patient.class).setParameter("name", lastName);
        return (Patient) query.getSingleResult();
    }

    @Override
    public void suppressPatient(Patient patientSuppress) {
        if (!em.contains(patientSuppress)) {
            patientSuppress = em.merge(patientSuppress);
        }
        em.remove(patientSuppress);
        em.flush();
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
    public Appointement createAppointement(Appointement newAppointement) {
        em.persist(newAppointement);
        em.flush();
        return newAppointement;
    }

    @Override
    public Appointement findAppointementById(Long id) {
        return em.find(Appointement.class, id);
    }

    @Override
    public void suppressAppointement(Appointement suppressAppointement) {
        if (!em.contains(suppressAppointement)) {
            suppressAppointement = em.merge(suppressAppointement);
        }
        em.remove(suppressAppointement);
        em.flush();
    }

    @Override
    public List<Appointement> findAppointementByDoctor(Doctor doctor) {
        Query query = em.createQuery("Appointement.findByDoctor", Appointement.class)
                .setParameter("doc", doctor);

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
    public Diagnosis createDiagnosis(Diagnosis newDiagnosis) {
        em.persist(newDiagnosis);
        em.flush();
        return newDiagnosis;
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
        if (!em.contains(suppressDiagnosis)) {
            suppressDiagnosis = em.merge(suppressDiagnosis);
        }
        em.remove(suppressDiagnosis);
        em.flush();
    }

    @Override
    public void suppressPosition(Position suppressPosition) {
      /*  System.out.println("id pos : " + suppressPosition.getId());
        Query query = em.createNativeQuery(
                "DELETE FROM gps_coordinates WHERE id_gpsc = " + suppressPosition.getId());
        System.out.println("nombre entite update : " + query.executeUpdate());*/
      if (!em.contains(suppressPosition)) {
          suppressPosition = em.merge(suppressPosition);
      }
        em.remove(suppressPosition);
        em.flush();

    }

    @Override
    public Position createPosition(Position newPosition) {
        em.persist(newPosition);
        em.flush();
        return newPosition;
    }

    @Override
    public Position findPositionById(Long id) {
        return em.find(Position.class, id);
    }

}
