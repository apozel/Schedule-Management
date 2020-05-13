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
import fr.isen.m1.schedule.marchant.moteur.MarchantDistanceCriticity;
import fr.isen.m1.schedule.random.RandomBuilder;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.Position;
import fr.isen.m1.schedule.utilities.Request;
import fr.isen.m1.schedule.utilities.SocialDetails;

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
                .addClasses(CrudPuInterface.class, CrudPuBean.class).addPackage(MarchantDistanceCriticity.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }



    @Test
    public void createDoctor() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertEquals(resultDoctorId.getPrenom(), randomDoctor.getPrenom());
        crud.createDoctor(randombuilder.buildRandomDoctor());
        List<Doctor> docList = crud.findAllDoctor();
        assertTrue(!docList.isEmpty());
        crud.createDoctor(randombuilder.buildRandomDoctor());
        docList = crud.findAllDoctor();
        assertTrue(!docList.isEmpty());
    }

    @Test
    public void findDoctorByName() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        Doctor resultDoctorId =
                crud.findDoctorByName(randomDoctor.getNom(), randomDoctor.getPrenom());
        assertEquals(resultDoctorId, randomDoctor);
    }

    @Test
    public void findDoctorById() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertEquals(resultDoctorId, randomDoctor);
    }

    @Test
    public void suppressDoctor() throws Exception {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        randomDoctor = crud.createDoctor(randomDoctor);
        Long idDoc = randomDoctor.getId();
        crud.suppressDoctor(randomDoctor);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertNull(resultDoctorId);
    }

    @Test
    public void suppressPosition() {
        Position testPosition = randombuilder.buildRandomPosition();
        testPosition = crud.createPosition(testPosition);
        Long idPosition = testPosition.getId();
        crud.suppressPosition(testPosition);
        Position resultPosition = crud.findPositionById(idPosition);
        assertNull(resultPosition);
    }

    @Test
    public void createPatient() {
        Patient randomPatient = randombuilder.buildRandomPatient();
        randomPatient = crud.createPatient(randomPatient);
        Long idPatient = randomPatient.getId();
        Patient resultPatientId = crud.findPatientById(idPatient);
        assertEquals(resultPatientId, randomPatient);
        crud.createPatient(randombuilder.buildRandomPatient());
        List<Patient> listPatient = crud.findAllPatient();
        assertTrue(!listPatient.isEmpty());

        crud.createPatient(randombuilder.buildRandomPatient());
        listPatient = crud.findAllPatient();

    }

    @Test
    public void findPatientById() {
        Patient randomPatient = randombuilder.buildRandomPatient();
        randomPatient = crud.createPatient(randomPatient);
        Long idPatient = randomPatient.getId();
        Patient resultPatientId = crud.findPatientById(idPatient);
        assertEquals(resultPatientId.getPrenom(), randomPatient.getPrenom());
    }

    @Test
    public void findPatientByName() {
        Patient randomPatient = randombuilder.buildRandomPatient();
        randomPatient = crud.createPatient(randomPatient);
        Long idPatient = randomPatient.getId();
        Patient resultPatientId = crud.findPatientByName(randomPatient.getNom());
        assertEquals(resultPatientId.getPrenom(), randomPatient.getPrenom());
    }

    @Test
    public void suppressPatient() {
        Position testPatient = randombuilder.buildRandomPosition();
        testPatient = crud.createPosition(testPatient);
        Long idPatient = testPatient.getId();
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
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Appointement appointement = randombuilder.buildRandomAppointement();
        appointement.setMedecinAffecte(doctor);
        appointement.setDiag(diagnosis);
        appointement.setMalade(patient);
        appointement.setLieu(patient.getLieuDeVie());
        appointement = crud.createAppointement(appointement);
        Long idAppointement = appointement.getId();
        Appointement resultAppointementId = crud.findAppointementById(idAppointement);
        assertEquals(resultAppointementId, appointement);

    }

    @Test
    public void findAppointementById() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Appointement appointement = randombuilder.buildRandomAppointement();
        appointement.setMedecinAffecte(doctor);
        appointement.setDiag(diagnosis);
        appointement.setMalade(patient);
        appointement.setLieu(patient.getLieuDeVie());
        appointement = crud.createAppointement(appointement);
        Long idAppointement = appointement.getId();
        Appointement resultAppointementId = crud.findAppointementById(idAppointement);
        assertEquals(resultAppointementId, appointement);
    }

    @Test
    public void findAppointementByDoctor() {
        // TODO: find appointement by doctor
    }

    @Test
    public void suppressAppointement() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Appointement appointement = randombuilder.buildRandomAppointement();
        appointement.setMedecinAffecte(doctor);
        appointement.setDiag(diagnosis);
        appointement.setMalade(patient);
        appointement.setLieu(patient.getLieuDeVie());
        appointement = crud.createAppointement(appointement);
        Long idAppointement = appointement.getId();
        crud.suppressAppointement(appointement);
        Appointement resultAppointement = crud.findAppointementById(idAppointement);
        assertNull(resultAppointement);
    }

    @Test
    public void findAppointementByDate() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Appointement appointement = randombuilder.buildRandomAppointement();
        appointement.setMedecinAffecte(doctor);
        appointement.setDiag(diagnosis);
        appointement.setMalade(patient);
        appointement.setLieu(patient.getLieuDeVie());
        Appointement appointement2 = new Appointement();
        appointement2.setMedecinAffecte(doctor);
        appointement2.setDiag(diagnosis);
        appointement2.setMalade(patient);
        appointement2.setLieu(patient.getLieuDeVie());
        appointement2.setDate(appointement.getDate().minusDays(1));
        Appointement appointement3 = new Appointement();
        appointement3.setMedecinAffecte(doctor);
        appointement3.setDiag(diagnosis);
        appointement3.setMalade(patient);
        appointement3.setLieu(patient.getLieuDeVie());
        appointement3.setDate(appointement.getDate().plusDays(1));
        System.out.println("appointementDate  1 : " + appointement.getDate());
        appointement = crud.createAppointement(appointement);
        appointement2 = crud.createAppointement(appointement2);
        appointement3 = crud.createAppointement(appointement3);
        List<Appointement> appointements =
                crud.findAppointementByDayDoctor(appointement.getDate(), doctor);
        System.out.println("appointementDate : " + appointement.getDate());
        System.out.println("appointement list : " + appointements);
        assertEquals(appointements.size(), 1);
    }

    @Test
    public void findAllDiagnosis() {
        // TODO:find all diagnosis
    }

    @Test
    public void createDiagnosis() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Long idDiagnosis = diagnosis.getId();
        Diagnosis resultDiagnosisId = crud.findDiagnosisById(idDiagnosis);
        assertEquals(resultDiagnosisId, diagnosis);
            }

    @Test
    public void findDiagnosisById() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Long idDiagnosis = diagnosis.getId();
        Diagnosis resultDiagnosisId = crud.findDiagnosisById(idDiagnosis);
        assertEquals(resultDiagnosisId, diagnosis);
    }

    @Test
    public void findDiagnosisByName() {
        //
    }

    @Test
    public void suppressDiagnosis() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Long idDiagnosis = diagnosis.getId();
        crud.suppressDiagnosis(diagnosis);
        Diagnosis resultDiagnosis = crud.findDiagnosisById(idDiagnosis);
        assertNull(resultDiagnosis);
    }

    public void findAppointementByDiagnosis() {

    }

    public void updateAppointement() {
    }

    @Test
    public void testAlgorithm() {
        Doctor doctor = randombuilder.buildRandomDoctor();
        doctor = crud.createDoctor(doctor);
        Patient patient = randombuilder.buildRandomPatient();
        patient = crud.createPatient(patient);
        Diagnosis diagnosis = randombuilder.buildRandomDiagnosis();
        diagnosis.setPatientConserne(patient);
        diagnosis = crud.createDiagnosis(diagnosis);
        Request newRequest = new Request(diagnosis, patient);
        System.out.println("first");
        algo.addAppointementSchedule(newRequest,doctor);
        assertEquals(1, crud.findAppointementByDoctor(doctor).size());
        Patient patient2 = randombuilder.buildRandomPatient();
        patient2 = crud.createPatient(patient2);
        Diagnosis diagnosis2 = randombuilder.buildRandomDiagnosis();
        diagnosis2.setPatientConserne(patient2);
        diagnosis2 = crud.createDiagnosis(diagnosis2);
        Request newRequest2 = new Request(diagnosis2, patient2);
        System.out.println("second");
        algo.addAppointementSchedule(newRequest2,doctor);
        assertEquals(2, crud.findAppointementByDoctor(doctor).size());
        Patient patient3 = randombuilder.buildRandomPatient();
        patient3 = crud.createPatient(patient3);
        Diagnosis diagnosis3 = randombuilder.buildRandomDiagnosis();
        diagnosis3.setPatientConserne(patient3);
        diagnosis3 = crud.createDiagnosis(diagnosis3);
        Request newRequest3 = new Request(diagnosis3, patient3);
        System.out.println("third : ");
        algo.addAppointementSchedule(newRequest3,doctor);
        assertEquals(3, crud.findAppointementByDoctor(doctor).size());
    }

}
