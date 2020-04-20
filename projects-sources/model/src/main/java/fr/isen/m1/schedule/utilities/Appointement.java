package fr.isen.m1.schedule.utilities;


import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@NamedQuery(name = "Appointement.findByDoctor", query = "SELECT a FROM Appointement a WHERE a.medecinAffecte = :doc")
public class Appointement implements Serializable {


    /**
    *
    */
    private static final long serialVersionUID = -3148976359143252003L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc")
    private Long id;
    @Column(name = "date_appoi")
    @Convert(converter = LocalDateConverter.class)
    private LocalDate date;
    @Column(name = "hours_appoi")
    @Convert(converter = LocalTimeConverter.class)
    private LocalTime heureDebut;
    @Transient
    private Duration dureeConsultation;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_gpsc")
    private Position lieu;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_doc", insertable=false, updatable=false)
    private Doctor medecinAffecte;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_medrec")
    private Patient malade;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_diag")
    private Diagnosis diag;

    public Appointement(){}
    public Appointement(LocalDate date, LocalTime heureDebut, Duration dureeConsultation,
            Doctor medecinAffecte, Patient malade, Diagnosis diag) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.dureeConsultation = dureeConsultation;
        this.medecinAffecte = medecinAffecte;
        this.malade = malade;
        this.diag = diag;
        this.lieu = malade.getLieuDeVie();
    }

    public Appointement(LocalDate date, LocalTime heureDebut, Duration dureeConsultation,
            Doctor docteur, Diagnosis diag) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.dureeConsultation = dureeConsultation;
        this.diag = diag;
        this.malade = this.diag.getPatientConserne();
        this.lieu = this.malade.getLieuDeVie();
        this.medecinAffecte = docteur;

    }

    public Appointement(Appointement rdvSource) {
        this.date = rdvSource.getDate();
        this.heureDebut = rdvSource.getHeureDebut();
        this.dureeConsultation = rdvSource.getDureeConsultation();
        this.lieu = rdvSource.getLieu();
        this.medecinAffecte = rdvSource.getMedecinAffecte();
        this.malade = rdvSource.getMalade();
        this.diag = rdvSource.getDiag();
        this.id = rdvSource.getId();
    }

    public Position getLieu() {
        return lieu;
    }

    public void setLieu(Position lieu) {
        this.lieu = lieu;
    }

    public Doctor getMedecinAffecte() {
        return medecinAffecte;
    }

    public void setMedecinAffecte(Doctor medecinAffecte) {
        this.medecinAffecte = medecinAffecte;
    }

    public Patient getMalade() {
        return malade;
    }

    public void setMalade(Patient malade) {
        this.malade = malade;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Duration getDureeConsultation() {
        return dureeConsultation;
    }

    public void setDureeConsultation(Duration dureeConsultation) {
        this.dureeConsultation = dureeConsultation;
    }

    public Diagnosis getDiag() {
        return diag;
    }

    public void setDiag(Diagnosis diag) {
        this.diag = diag;
    }



    @Override
    public String toString() {
        return "IDRendezVous = " + id + ",\n date=" + date + ",\n diag=" + diag
                + ",\n dureeConsultation=" + dureeConsultation + ",\n heureDebut=" + heureDebut
                + ",\n malade=" + malade + "\n";
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
