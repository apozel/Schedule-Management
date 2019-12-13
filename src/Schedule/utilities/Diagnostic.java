package Schedule.utilities;

import java.util.UUID;

/**
 * Diagnostic
 */
public class Diagnostic {

    private final String diagnosticId = UUID.randomUUID().toString();;
    private int criticite;
    private String description;
    private String patientId;
    private Patient patientConserne;

    public Diagnostic(int criticite, String description, Patient patient) {
        this.criticite = criticite;
        this.description = description;
        this.patientId = patient.getIDPatient();
        this.patientConserne = patient;
    }

    public String getDiagnosticId() {
        return diagnosticId;
    }

    public int getCriticite() {
        return criticite;
    }

    public void setCriticite(int criticite) {
        this.criticite = criticite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Patient getPatientConserne() {
        return patientConserne;
    }

    public void setPatientConserne(Patient patientConserne) {
        this.patientConserne = patientConserne;
    }

}