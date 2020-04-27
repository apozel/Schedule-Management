package fr.isen.m1.schedule.random;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import fr.isen.m1.schedule.utilities.Appointement;
import fr.isen.m1.schedule.utilities.Diagnosis;
import fr.isen.m1.schedule.utilities.Doctor;
import fr.isen.m1.schedule.utilities.Patient;
import fr.isen.m1.schedule.utilities.SocialDetails;

/**
 * Unit test for simple App.
 */
public class NameTest {

    RandomBuilder randomBuilder = new RandomBuilder();

    @Test
    public void testRandomSocialDetails() {
        SocialDetails socialDetails = randomBuilder.buildRandomSocialDetails();
        System.out.println(socialDetails.toString());
        assertNotNull(socialDetails.getBirthAddress());
        assertNotNull(socialDetails.getBirthDate());
        assertNotNull(socialDetails.getBirthZipCode());
        assertNotNull(socialDetails.getFirstName());
        assertNotNull(socialDetails.getLastName());
        assertNotNull(socialDetails.getPhoneNumber());
    }

    @Test
    public void testRandomPatient() {
        Patient testPat = randomBuilder.buildRandomPatient();
        assertNotNull(testPat.getDetails());
        assertNotNull(testPat.getLieuDeVie());

    }

    @Test
    public void testRandomDoctor() {
        Doctor testDoctor = randomBuilder.buildRandomDoctor();
        System.out.println(testDoctor.toString());
        assertNotNull(testDoctor.getDetails());
        assertNotNull(testDoctor.getCdhp());
        assertNotNull(testDoctor.getLieuDeDepart());
        assertNotNull(testDoctor.getEmplacement());
    }

    @Test
    public void testRandomDiagnosis() {
        Diagnosis testDiagnosis = randomBuilder.buildRandomDiagnosis();
        assertNotNull(testDiagnosis.getPatientConserne());
        assertNotNull(testDiagnosis.getCriticite());
        assertNotNull(testDiagnosis.getDescription());
    }

    @Test
    public void testRandomAppointement() {
        Appointement appointement = randomBuilder.buildRandomAppointement();
        assertNotNull(appointement);
    }

}
