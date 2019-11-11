
/**
 * Demande
 */
public class Demande {

    private Diagnostic diag;
    private Patient patientConsernee; 

    public Demande(Diagnostic diag, Patient patientConsernee) {
        this.diag = diag;
        this.patientConsernee = patientConsernee;
    }

    public Diagnostic getDiag() {
        return diag;
    }

    public void setDiag(Diagnostic diag) {
        this.diag = diag;
    }

    public Patient getPatientConsernee() {
        return patientConsernee;
    }

    public void setPatientConsernee(Patient patientConsernee) {
        this.patientConsernee = patientConsernee;
    }

    
}