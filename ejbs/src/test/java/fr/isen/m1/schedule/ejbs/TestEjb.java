package fr.isen.m1.schedule.ejbs;

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
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Position;

@RunWith(Arquillian.class)
public class TestEjb {

    @EJB
    private AlgorithmInterface algo;
    @EJB
    private CrudPuInterface crud;


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(Doctor.class.getPackage())
                .addPackage(BooleanConverter.class.getPackage())
                .addPackage(SocialDetailsBuilder.class.getPackage())
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


    public void createDoctor() {
        Doctor jeanLasalle = new Doctor("chaillan", "jean", "11115", new Position(10, 10));
        Long idDoc = crud.createDoctor(jeanLasalle);
        System.out.println(idDoc);
        Doctor jean = crud.findDoctorById(1L);
        assertTrue(jean.getPrenom().equals("jean"));
        List<Doctor> doc = crud.findAllDoctor();
        System.out.println(doc);
        assertTrue(!doc.isEmpty());
    }

    @Test
    public void findDoctor() {
        Doctor jean = crud.findDoctorById(1L);
        assertTrue(jean.getPrenom().equals("jean"));
    }


    @Test
    public void firstTestDao() {
        List<Doctor> doc = crud.findAllDoctor();
        System.out.println(doc);
        assertTrue(!doc.isEmpty());
    }


}
