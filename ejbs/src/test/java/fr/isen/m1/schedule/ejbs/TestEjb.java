package fr.isen.m1.schedule.ejbs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import fr.isen.m1.schedule.builder.SocialDetailsBuilder;
import fr.isen.m1.schedule.converter.BooleanConverter;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;
import fr.isen.m1.schedule.ejbs.ejbinterface.CrudPuInterface;
import fr.isen.m1.schedule.ejbs.implementation.AlgorithmBean;
import fr.isen.m1.schedule.ejbs.implementation.CrudPuBean;
import fr.isen.m1.schedule.random.RandomBuilder;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;

@RunWith(Arquillian.class)

public class TestEjb {

    @EJB
    private AlgorithmInterface algo;
    @EJB
    private CrudPuInterface crud;

    private RandomBuilder randombuilder = new RandomBuilder();


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(Doctor.class.getPackage())
                .addPackage(BooleanConverter.class.getPackage())
                .addPackage(SocialDetailsBuilder.class.getPackage()).addClass(RandomBuilder.class)
                .addAsResource("META-INF/persistence.xml").addAsResource("META-INF/sql/Script.sql")
                .addAsResource("META-INF/sql/Drop.sql")
                .addClasses(AlgorithmBean.class, AlgorithmInterface.class)
                .addClasses(CrudPuInterface.class, CrudPuBean.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test()
    public void sayHelloTest() {
        Assert.assertEquals(algo.helloWord(), "Hello World");
    }

    @Test
    public void createDoctor() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        System.out.println("random id doc : " + idDoc);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertEquals(resultDoctorId.getPrenom(), randomDoctor.getPrenom());
        crud.createDoctor(randombuilder.buildRandomDoctor());
        List<Doctor> docList = crud.findAllDoctor();
        System.out.println(docList);
        assertTrue(!docList.isEmpty());
        crud.createDoctor(randombuilder.buildRandomDoctor());
        docList = crud.findAllDoctor();
        System.out.println("nombre doctor : " + docList.size());
        assertTrue(!docList.isEmpty());
    }

    @Test
    public void findDoctorByName() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        System.out.println("random id doc : " + idDoc);
        Doctor resultDoctorId = crud.findDoctorByName(randomDoctor.getNom());
        assertEquals(resultDoctorId, randomDoctor);
    }

    @Test
    public void findDoctorById() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        System.out.println("random id doc : " + idDoc);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertEquals(resultDoctorId, randomDoctor);
    }

    @Test
    public void suppressDoctor() throws Exception {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        System.out.println("random id doc : " + idDoc);
        crud.suppressDoctor(randomDoctor);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        System.out.println("docteur supprimer : " + resultDoctorId);
        assertNull(resultDoctorId);
    }

    @Test
    public void suppressPosition() {
        Position testPosition = randombuilder.buildRandomPosition();
        testPosition = crud.createPosition(testPosition);
        Long idPosition = testPosition.getId();
        System.out.println("id after creation : (idPosition) " + idPosition);
        System.out.println("id after creation : (testPosition) " + testPosition.getId());
        crud.suppressPosition(testPosition);
        Position resultPosition = crud.findPositionById(idPosition);
        assertNull(resultPosition);
    }

    @Test
    public void createPatient() {
        Patient randomPatient = randombuilder.buildRandomPatient();
        randomPatient = crud.createPatient(randomPatient);
        Long idPatient = randomPatient.getId();
        System.out.println("random id patient : " + idPatient);
        Patient resultPatientId = crud.findPatientById(idPatient);
        assertEquals(resultPatientId, randomPatient);
        crud.createPatient(randombuilder.buildRandomPatient());
        List<Patient> listPatient = crud.findAllPatient();
        System.out.println(listPatient);
        assertTrue(!listPatient.isEmpty());

        crud.createPatient(randombuilder.buildRandomPatient());
        listPatient = crud.findAllPatient();

    }

    @Test
    public void findPatientById() {
        Patient randomPatient = randombuilder.buildRandomPatient();
        randomPatient = crud.createPatient(randomPatient);
        Long idPatient = randomPatient.getId();
        System.out.println("random id doc : " + idPatient);
        Patient resultPatientId = crud.findPatientById(idPatient);
        assertEquals(resultPatientId.getPrenom(), randomPatient.getPrenom());
    }

    @Test
    public void findPatientByName() {
        Patient randomPatient = randombuilder.buildRandomPatient();
        randomPatient = crud.createPatient(randomPatient);
        Long idPatient = randomPatient.getId();
        System.out.println("random id doc : " + idPatient);
        Patient resultPatientId = crud.findPatientByName(randomPatient.getNom());
        assertEquals(resultPatientId.getPrenom(), randomPatient.getPrenom());
    }

    @Test
    public void suppressPatient() {
        Position testPatient = randombuilder.buildRandomPosition();
        testPatient = crud.createPosition(testPatient);
        Long idPatient = testPatient.getId();
        System.out.println("id after creation : (idPatient) " + idPatient);
        System.out.println("id after creation : (testPatient) " + testPatient.getId());
        crud.suppressPosition(testPatient);
        Position posResult = crud.findPositionById(idPatient);
        assertNull(posResult);
    }

    @Test
    public void findAllAppointement() {
        // TODO: find all appointement
    }

    @Test
    public void createAppointement() {
        Appointement randomAppointement = randombuilder.buildRandomAppointement();
        randomAppointement = crud.createAppointement(randomAppointement);
        Long idAppointement = randomAppointement.getId();
        System.out.println("random id Appointement : " + idAppointement);
        Appointement resultAppointementId = crud.findAppointementById(idAppointement);
        assertEquals(resultAppointementId, randomAppointement);
        crud.createAppointement(randombuilder.buildRandomAppointement());
        List<Appointement> listAppointement = crud.findAllAppointement();
        System.out.println(listAppointement);
        assertTrue(!listAppointement.isEmpty());
        crud.createAppointement(randombuilder.buildRandomAppointement());
        listAppointement = crud.findAllAppointement();
        assertTrue(!listAppointement.isEmpty());
    }

    @Test
    public void findAppointementById() {
        Appointement randomAppointement = randombuilder.buildRandomAppointement();
        randomAppointement = crud.createAppointement(randomAppointement);
        Long idAppointement = randomAppointement.getId();
        System.out.println("random id doc : " + idAppointement);
        Appointement resultAppointementId = crud.findAppointementById(idAppointement);
        assertEquals(resultAppointementId, randomAppointement);
    }

    @Test
    public void findAppointementByDoctor() {
        // TODO: find appointement by doctor
    }

    @Test
    public void suppressAppointement() {
        Appointement testAppointement = randombuilder.buildRandomAppointement();
        testAppointement = crud.createAppointement(testAppointement);
        Long idAppointement = testAppointement.getId();
        System.out.println("id after creation : (idAppointement) " + idAppointement);
        System.out.println("id after creation : (testAppointement) " + testAppointement.getId());
        crud.suppressAppointement(testAppointement);
        Appointement resultAppointement = crud.findAppointementById(idAppointement);
        assertNull(resultAppointement);
    }

    @Test
    public void findAppointementByDate() {
        // TODO: find appointement by date
    }

    @Test
    public void findAllDiagnosis() {
        // TODO:find all diagnosis
    }

    @Test
    public void createDiagnosis() {
        Diagnosis randomDiagnosis = randombuilder.buildRandomDiagnosis();
        randomDiagnosis = crud.createDiagnosis(randomDiagnosis);
        Long idDiagnosis = randomDiagnosis.getId();
        System.out.println("random id Diagnosis : " + idDiagnosis);
        Diagnosis resultDiagnosisId = crud.findDiagnosisById(idDiagnosis);
        assertEquals(resultDiagnosisId, randomDiagnosis);
        crud.createDiagnosis(randombuilder.buildRandomDiagnosis());
        List<Diagnosis> listDiagnosis = crud.findAllDiagnosis();
        System.out.println(listDiagnosis);
        assertTrue(!listDiagnosis.isEmpty());
        crud.createDiagnosis(randombuilder.buildRandomDiagnosis());
        listDiagnosis = crud.findAllDiagnosis();
        assertTrue(!listDiagnosis.isEmpty());
    }

    @Test
    public void findDiagnosisById() {
        Diagnosis randomDiagnosis = randombuilder.buildRandomDiagnosis();
        randomDiagnosis = crud.createDiagnosis(randomDiagnosis);
        Long idDiagnosis = randomDiagnosis.getId();
        System.out.println("random id doc : " + idDiagnosis);
        Diagnosis resultDiagnosisId = crud.findDiagnosisById(idDiagnosis);
        assertEquals(resultDiagnosisId, randomDiagnosis);
    }

    @Test
    public void findDiagnosisByName() {
        // TODO: find diagnosis by name of doctor
    }

    @Test
    public void suppressDiagnosis() {
        Diagnosis testDiagnosis = randombuilder.buildRandomDiagnosis();
        testDiagnosis = crud.createDiagnosis(testDiagnosis);
        Long idDiagnosis = testDiagnosis.getId();
        System.out.println("id after creation : (idDiagnosis) " + idDiagnosis);
        System.out.println("id after creation : (testDiagnosis) " + testDiagnosis.getId());
        crud.suppressDiagnosis(testDiagnosis);
        Diagnosis resultDiagnosis = crud.findDiagnosisById(idDiagnosis);
        assertNull(resultDiagnosis);
    }
}
