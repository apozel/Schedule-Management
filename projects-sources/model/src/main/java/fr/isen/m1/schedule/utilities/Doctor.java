package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Arrays;
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
import javax.persistence.Transient;
import fr.isen.m1.schedule.builder.SocialDetailsBuilder;

/**
 * docteur
 */
@Entity
@Table(name = "Doctor")
@NamedQueries({@NamedQuery(name = "Doctor.findAll", query = "SELECT doctor FROM Doctor doctor"),
        @NamedQuery(name = "Doctor.findByName",
                query = "SELECT doctor FROM Doctor doctor INNER JOIN doctor.details details WHERE details.lastName = :lastName AND details.firstName = :firstName")})
public class Doctor implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -3373115033031181964L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doc")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_socdet")
    private SocialDetails details;
    @Column(name = "CDHP")
    private String cdhp;
    @Transient
    private Position emplacement;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_gpsc")
    private Position lieuDeDepart;
    @Transient
    private LocalTime[] horaires = new LocalTime[] {LocalTime.of(8, 0), LocalTime.of(12, 0),
            LocalTime.of(13, 0), LocalTime.of(18, 0)};

    public Doctor() {
    }

    public Doctor(String nom, String prenom, String cdhp, Position lieuDeDepart) {

        this.details = new SocialDetailsBuilder().setFirstName(prenom).setLastName(nom).build();
        this.cdhp = cdhp;
        this.lieuDeDepart = lieuDeDepart;
        this.emplacement = lieuDeDepart;
    }


    public String getNom() {
        return this.details.getLastName();
    }

    public void setNom(String nom) {
        this.details.setLastName(nom);
    }

    public String getPrenom() {
        return this.details.getFirstName();
    }

    public void setPrenom(String prenom) {
        this.details.setFirstName(prenom);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SocialDetails getDetails() {
        return details;
    }

    public void setDetails(SocialDetails details) {
        this.details = details;
    }



    @Override
    public String toString() {
        return "Docteur " + this.getNom() + " " + this.getPrenom();
    }

    public Position getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Position emplacement) {
        this.emplacement = emplacement;
    }



    public LocalTime getHoraires(int index) {

        return horaires[index];
    }

    public void setHoraires(LocalTime[] horaires) {
        this.horaires = horaires;
    }

    public Position getLieuDeDepart() {
        return lieuDeDepart;
    }

    public void setLieuDeDepart(Position lieuDeDepart) {
        this.lieuDeDepart = lieuDeDepart;
    }

    /**
     * @return the cdhp
     */
    public String getCdhp() {
        return cdhp;
    }

    /**
     * @param cdhp the cdhp to set
     */
    public void setCdhp(String cdhp) {
        this.cdhp = cdhp;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

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
        Doctor other = (Doctor) obj;
        return Objects.equals(details, other.details) && Objects.equals(id, other.id);
    }

}
