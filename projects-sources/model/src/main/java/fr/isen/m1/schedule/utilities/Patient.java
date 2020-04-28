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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import fr.isen.m1.schedule.builder.SocialDetailsBuilder;

/**
 * Patient
 */
@Entity
@Table(name = "patient_medical_record")
@NamedQueries({@NamedQuery(name = "Patient.findAll", query = "SELECT patient FROM Patient patient"),
        @NamedQuery(name = "Patient.findByName",
                query = "SELECT patient FROM Patient patient INNER JOIN patient.details details WHERE details.lastName = :name")})
public class Patient implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 9097542296323042841L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medrec")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_gpsc")
    private Position lieuDeVie;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_socdet")
    private SocialDetails details;

    public Patient() {
    }

    public Patient(Position lieuDeVie, String lastName, String firstName) {
        this.lieuDeVie = lieuDeVie;
        this.details = new SocialDetailsBuilder().build();
        this.setNom(lastName);
        this.setPrenom(firstName);

    }

    public Long getId() {
        return id;
    }

    public Position getLieuDeVie() {
        return lieuDeVie;
    }

    public void setLieuDeVie(Position lieuDeVie) {
        this.lieuDeVie = lieuDeVie;
    }

    public String getNom() {
        return this.details.getLastName();
    }

    public void setNom(String lastName) {
        this.details.setLastName(lastName);
    }

    public String getPrenom() {
        return details.getFirstName();
    }

    public void setPrenom(String firstName) {
        this.details.setFirstName(firstName);
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }

    /**
     * @return the details
     */
    public SocialDetails getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(SocialDetails details) {
        this.details = details;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(details, id);
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
        Patient other = (Patient) obj;
        return Objects.equals(details, other.details) && Objects.equals(id, other.id);
    }

}
