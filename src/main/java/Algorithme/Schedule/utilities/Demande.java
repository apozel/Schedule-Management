package bigData.Schedule.utilities;

/**
 * Demande
 */
public class Demande {

    private Diagnostic diag;
    private Patient malade; 

    public Demande(Diagnostic diag, Patient malade) {
        this.diag = diag;
        this.malade = malade;
    }

    public Diagnostic getDiag() {
        return diag;
    }

    public void setDiag(Diagnostic diag) {
        this.diag = diag;
    }

    public Patient getMalade() {
        return malade;
    }

    public void setMalade(Patient patientConsernee) {
        this.malade = patientConsernee;
    }

    @Override
    public String toString() {
        return "Demande [diag=" + diag + ", malade=" + malade + "]";
    }

    
}