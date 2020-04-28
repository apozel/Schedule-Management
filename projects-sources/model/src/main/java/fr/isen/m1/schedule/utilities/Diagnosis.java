package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Diagnostic
 */
@Entity
@Table(name = "doctor_diagnostic")
public class Diagnosis implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -4171315315937989991L;

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
        return Objects.hash(id, patientConserne);
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
                && Objects.equals(patientConserne, other.patientConserne);
    }
}
