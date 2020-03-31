package Algorithm.Schedule.utilities;

import java.util.UUID;

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
import javax.persistence.Transient;

import Algorithm.Schedule.builder.SocialDetailsBuilder;

/**
 * Patient
 */
@Entity
@Table(name = "patient_medical_record")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medrec")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_gpsc")
    private Position lieuDeVie;

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_socdet")
    private SocialDetails details;
    @Transient
    private final String IDPatient = UUID.randomUUID().toString();

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((IDPatient == null) ? 0 : IDPatient.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Patient other = (Patient) obj;
        if (IDPatient == null) {
            if (other.IDPatient != null)
                return false;
        } else if (!IDPatient.equals(other.IDPatient))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom();
    }

    public String getIDPatient() {
        return IDPatient;
    }

}