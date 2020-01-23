package Schedule.enregistrement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.time.Duration;
import java.time.LocalDateTime;

import Schedule.utilities.Diagnostic;
import Schedule.utilities.Docteur;
import Schedule.utilities.Patient;
import Schedule.utilities.Position;
import Schedule.utilities.RendezVous;

import com.google.gson.*;


/**
 * testEnregistrement
 */
public class testEnregistrement {

    private File fichier = new File("C:/Users/tomch/Documents/java/Schedule-Management/src/Ressources/Json/test.json");
    private FileWriter ecritureStream = null;
    private LocalDateTime DateHeureActuel = LocalDateTime.now();
    private Gson trad = new GsonBuilder().setPrettyPrinting().create();
    private Docteur testDocteur = new Docteur("arthur", "saucisson", "mentoniste", new Position(90, 70));
    private Patient testPatient = new Patient(new Position(11, 3), "Rafael", "le clown");
    private RendezVous testRendezVous = new RendezVous(DateHeureActuel.toLocalDate(),
            testDocteur.getHoraires(0).of(10, 0), Duration.ofMinutes(45), testDocteur,
            new Diagnostic(1, "gastro", testPatient));


   

    public void testCreationGson() {
        InputStream myStream = new FileInputStream("C:/Users/tomch/Documents/java/Schedule-Management/src/Ressources/Json/test.json");
String myString = IOUtils.toString(myStream);
    }

    public void simpleGsonTest(){
        ecritureFichier(trad.toJson(testRendezVous));
    }

    public void ecritureFichier(String aEcrire){
        try {
            this.ecritureStream.write(aEcrire);
        this.ecritureStream.flush();
        } catch (Exception e) {
            
        }
        
    }

  

    public static void main(String[] args) {
        testEnregistrement test = new testEnregistrement();

        try {
            test.simpleGsonTest();

        } catch (Exception e) {
            
        }

    }

    public Writer getEcritureStream() {
        return ecritureStream;
    }

    public void setEcritureStream(FileWriter ecritureStream) {
        this.ecritureStream = ecritureStream;
    }

}