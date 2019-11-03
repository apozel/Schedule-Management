
/**
 * Diagnostic
 */
public class Diagnostic {

    private int diagnosticId;
    private int criticite;
    private String description;
    private int patientId;
    private Patient patientConserne;

    public Diagnostic(int diagnosticId, int criticite, String description, int patientId) {
        this.diagnosticId = diagnosticId;
        this.criticite = criticite;
        this.description = description;
        this.patientId = patientId;
    }

    public int getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(int diagnosticId) {
        this.diagnosticId = diagnosticId;
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Patient getPatientConserne() {
        return patientConserne;
    }

    public void setPatientConserne(Patient patientConserne) {
        this.patientConserne = patientConserne;
    }

    
}