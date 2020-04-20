package fr.isen.m1.schedule.ejbs.implementation;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import fr.isen.m1.schedule.ejbs.ejbinterface.CrudPuInterface;
import fr.isen.m1.schedule.utilities.Doctor;

@Stateless(mappedName = "CrudPuInterface")
public class CrudPuBean implements CrudPuInterface {

    @PersistenceContext(unitName = "schedulePU")
    EntityManager em;
   // private static Logger logger = Logger.getLogger(CrudPuBean.class);

    @Override
    public List<Doctor> findAllDoctor() {
       // logger.info("doctor find all");
        Query query = em.createNamedQuery("Doctor.findAll");
        @SuppressWarnings("unchecked")
        List<Doctor> Doctors = query.getResultList();
       // logger.debug(Doctors);
        return Doctors;
    }

    @Override
    public Long createDoctor(Doctor newDoc) {
        //logger.info("create Doctor");
        em.persist(newDoc);
       // logger.debug("new doc : " + newDoc);
       // logger.debug("new doc id : " + newDoc.getId());
        return newDoc.getId();

    }

    @Override
    public Doctor findDoctorById(Long id) {
        return em.find(Doctor.class, id);
    }



}
