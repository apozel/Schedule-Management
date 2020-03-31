package Algorithm.Schedule.utilities;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Diagnostic
 */

public class Diagnosis {

    private final String diagnosticId = UUID.randomUUID().toString();;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diag")
    private Long id;
    @Column(name = "gravityGrade")
    private int criticite;
    @Column(name = "report")
    private String description;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_medrec")
    private Patient patientConserne;

    public Diagnosis() {

    }

    public Diagnosis(int criticite, String description, Patient patient) {
        this.criticite = criticite;
        this.description = description;
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



    public void setDescription(String description) {
        this.description = description;
    }


    public Patient getPatientConserne() {
        return patientConserne;
    }

    public void setPatientConserne(Patient patientConserne) {
        this.patientConserne = patientConserne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}