package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Diagnostic
 */
@Entity
@Table(name = "doctor_diagnostic")
@NamedQueries({@NamedQuery(name = "Diagnosis.findAll",
        query = "SELECT diagnosis FROM Diagnosis diagnosis"),
        // @NamedQuery(name = "Diagnosis.findByName",query = "SELECT diagnosis FROM Diagnosis
        // diagnosis INNER JOIN diagnosis.details details WHERE details.lastName = :name")
})
public class Diagnosis implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -4171315315937989991L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diag")
    private Long id;
    @Column(name = "criticity")
    private float criticity;
    @Transient
    private String description;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medrec")
    private Patient patient;

    public Diagnosis() {

    }

    public Diagnosis(float criticite, String description, Patient patient) {
        this.criticity = criticite;
        this.description = description;
        this.patient = patient;
    }



    public float getCriticite() {
        return criticity;
    }

    public void setCriticite(float criticite) {
        this.criticity = criticite;
    }



    public void setDescription(String description) {
        this.description = description;
    }


    public Patient getPatientConserne() {
        return patient;
    }

    public void setPatientConserne(Patient patientConserne) {
        this.patient = patientConserne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(id, patient);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Diagnosis other = (Diagnosis) obj;
        return Objects.equals(id, other.id)
                && Objects.equals(patient, other.patient);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Diagnosis [id=" + id + ", patientConserne=" + patient + "]";
    }
}
