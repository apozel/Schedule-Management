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
import fr.isen.m1.schedule.utilities.Doctor;
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
                .addPackage(SocialDetailsBuilder.class
                        .getPackage())
                .addClass(RandomBuilder.class)
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

    //@Test
    public void createDoctor() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        Long idDoc = crud.createDoctor(randomDoctor);
        System.out.println("random id doc : "+idDoc);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertEquals(resultDoctorId.getPrenom(), randomDoctor.getPrenom());
        crud.createDoctor(randombuilder.buildRandomDoctor());
        List<Doctor> docList = crud.findAllDoctor();
        System.out.println(docList);
        assertTrue(!docList.isEmpty());
        assertEquals(docList.size(), 2);
        crud.createDoctor(randombuilder.buildRandomDoctor());
        docList = crud.findAllDoctor();
        assertEquals(docList.size(), 3);
    }

    //@Test
    public void findDoctorByName() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        Long idDoc = crud.createDoctor(randomDoctor);
        System.out.println("random id doc : " + idDoc);
        Doctor resultDoctorId = crud.findDoctorByName(randomDoctor.getNom());
        assertEquals(resultDoctorId.getPrenom(), randomDoctor.getPrenom());
    }

    @Test
    public void suppressDoctor() throws Exception {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        Long idDoc = crud.createDoctor(randomDoctor);
        System.out.println("random id doc : " + idDoc);
        crud.suppressDoctor(randomDoctor);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertNull(resultDoctorId);
    }

   // @Test
    public void findAllPatient() {
        Doctor randomDoctor = randombuilder.buildRandomDoctor();
        Long idDoc = crud.createDoctor(randomDoctor);
        System.out.println("random id doc : " + idDoc);
        Doctor resultDoctorId = crud.findDoctorById(idDoc);
        assertEquals(resultDoctorId.getPrenom(), randomDoctor.getPrenom());
        crud.createDoctor(randombuilder.buildRandomDoctor());
        List<Doctor> docList = crud.findAllDoctor();
        System.out.println(docList);
        assertTrue(!docList.isEmpty());
        assertEquals(docList.size(), 2);
        crud.createDoctor(randombuilder.buildRandomDoctor());
        docList = crud.findAllDoctor();
        assertEquals(docList.size(), 3);
    }

    @Test
    public void createPatient() {

    }

    @Test
    public void findPatientById() {

    }

    @Test
    public void findPatientByName() {

    }

    @Test
    public void suppressPatientById() {

    }

    @Test
    public void findAllAppointement() {

    }

    @Test
    public void createAppointement() {

    }

    @Test
    public void findAppointementById() {

    }

    @Test
    public void suppressAppointementById() {

    }

    @Test
    public void findAllDiagnosis() {

    }

    @Test
    public void createDiagnosis() {

    }

    @Test
    public void findDiagnosisById() {

    }

    @Test
    public void findDiagnosisByName() {

    }

    @Test
    public void suppressDiagnosisById(){

    }
}
