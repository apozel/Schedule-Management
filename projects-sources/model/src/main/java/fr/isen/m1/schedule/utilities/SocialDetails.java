package fr.isen.m1.schedule.utilities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import fr.isen.m1.schedule.converter.BooleanConverter;

/**
 * SocialDetails
 */

@Entity
@Table(name = "social_details")
public class SocialDetails implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 2649326094784357894L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socdet")
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "gender")
    @Convert(converter = BooleanConverter.class)
    private boolean gender;
    @Column(name = "birthaddr")
    private String birthAddress;
    @Column(name = "birthZipCode")
    private String birthZipCode;
    @Column(name = "birthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "picture")
    private String picture;
    @OneToOne(mappedBy = "socialDetails")
    @JoinColumn(name = "id_doc")
    private Doctor idDoc;


    public SocialDetails() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
    }

    public String getBirthZipCode() {
        return birthZipCode;
    }

    public void setBirthZipCode(String birthZipCode) {
        this.birthZipCode = birthZipCode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Doctor getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Doctor idDoc) {
        this.idDoc = idDoc;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "SocialDetails [birthAddress=" + birthAddress + ", birthDate=" + birthDate
                + ", birthZipCode=" + birthZipCode + ", firstName=" + firstName + ", gender="
                + gender + ", id=" + id + ", idDoc=" + idDoc + ", lastName=" + lastName
                + ", phoneNumber=" + phoneNumber + ", picture=" + picture + "]";
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
        SocialDetails other = (SocialDetails) obj;
        return Objects.equals(id, other.id);
    }


}