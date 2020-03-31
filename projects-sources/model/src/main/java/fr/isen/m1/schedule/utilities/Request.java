package fr.isen.m1.schedule.utilities;

/**
 * Demande
 */
public class Request {

    private Diagnosis diag;
    private Patient malade;

    public Request(Diagnosis diag, Patient malade) {
        this.diag = diag;
        this.malade = malade;
    }

    public Diagnosis getDiag() {
        return diag;
    }

    public void setDiag(Diagnosis diag) {
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