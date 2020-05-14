package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import fr.isen.m1.schedule.converter.LocalDateConverter;
import fr.isen.m1.schedule.converter.LocalTimeConverter;

/**
 * RendezVous
 */

@Entity
@Table(name = "appointement")
@NamedQueries({
        @NamedQuery(name = "Appointement.findByDoctor",
                query = "SELECT appointement FROM Appointement appointement WHERE appointement.doctor = :doc"),
        @NamedQuery(name = "Appointement.findAll",
                query = "SELECT appointement FROM Appointement appointement"),
        @NamedQuery(name = "Appointement.findByDayDoctor",
                query = "SELECT appointement FROM Appointement appointement WHERE appointement.doctor = :doctor AND appointement.date = :day"),
            @NamedQuery(name = "Appointement.findByDiagnosis",
                query = "SELECT appointement FROM Appointement appointement WHERE appointement.diagnosis = :diagnosis")
            })

public class Appointement implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -3148976359143252003L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appoi")
    private Long id;
    @Column(name = "date_appoi")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;
    @Column(name = "hours_appoi")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime hoursAppointement;
    @Transient
    private Duration consultationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gpsc")
    private Position lieu;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_doc")
    private Doctor doctor;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medrec")
    private Patient patient;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_diag")
    private Diagnosis diagnosis;

    public Appointement() {
    }

    public Position getLieu() {
        return lieu;
    }

    public void setLieu(Position lieu) {
        this.lieu = lieu;
    }

    public Doctor getMedecinAffecte() {
        return doctor;
    }

    public void setMedecinAffecte(Doctor medecinAffecte) {
        this.doctor = medecinAffecte;
    }

    public Patient getMalade() {
        return patient;
    }

    public void setMalade(Patient malade) {
        this.patient = malade;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return hoursAppointement;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.hoursAppointement = heureDebut;
    }

    public Duration getDureeConsultation() {
        return consultationTime;
    }

    public void setDureeConsultation(Duration dureeConsultation) {
        this.consultationTime = dureeConsultation;
    }

    public Diagnosis getDiag() {
        return diagnosis;
    }

    public void setDiag(Diagnosis diag) {
        this.diagnosis = diag;
    }

    @Override
    public String toString() {
        return "IDRendezVous = " + id + ",\n date=" + date + ",\n diag=" + diagnosis
                + ",\n dureeConsultation=" + consultationTime + ",\n heureDebut=" + hoursAppointement
                + ",\n malade=" + patient + "\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
        Appointement other = (Appointement) obj;
        return Objects.equals(id, other.id);
    }

}
