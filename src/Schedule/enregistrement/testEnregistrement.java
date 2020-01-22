package Schedule.enregistrement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;

import Schedule.utilities.Diagnostic;
import Schedule.utilities.Docteur;
import Schedule.utilities.Patient;
import Schedule.utilities.Position;
import Schedule.utilities.RendezVous;

/**
 * testEnregistrement
 */
public class testEnregistrement {

    private File fichier = new File("C:/Users/tomch/Documents/java/Schedule-Management/src/Ressources/Json/test.json");
    private FileWriter ecritureStream = null;
    private LocalDateTime DateHeureActuel = LocalDateTime.now();
    private Docteur testDocteur = new Docteur("arthur", "saucisson", "mentoniste", new Position(90, 70));
    private Patient testPatient = new Patient(new Position(11, 3), "Rafael", "le clown");
    private RendezVous testRendezVous = new RendezVous(DateHeureActuel.toLocalDate(),
            testDocteur.getHoraires(0).of(10, 0), Duration.ofMinutes(45), testDocteur,
            new Diagnostic(1, "gastro", testPatient));


    public testEnregistrement() {
        try {
            this.ecritureStream = new FileWriter(fichier);
            // this.fos =new FileOutputStream(fichier);
        } catch (Exception e) {
            System.out.println("erreur");
        }

    }

    public void writeJsonSimpleDemo() throws Exception {
        // First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        // Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        // Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.put(employeeObject);
        employeeList.put(employeeObject2);

        this.ecritureStream.write(employeeList.toString());
        this.ecritureStream.flush();
    }

    public static void main(String[] args) {
        testEnregistrement test = new testEnregistrement();

        try {
            test.writeJsonSimpleDemo();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public Writer getEcritureStream() {
        return ecritureStream;
    }

    public void setEcritureStream(FileWriter ecritureStream) {
        this.ecritureStream = ecritureStream;
    }

}