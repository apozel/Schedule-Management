package Algorithm.Schedule.utilities;

import java.util.Date;

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

import Algorithm.Schedule.converter.BooleanConverter;

/**
 * SocialDetails
 */

@Entity
@Table(name = "social_details")
public class SocialDetails {

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
    @OneToOne(mappedBy = "details")
    @JoinColumn(name = "id_doc")
    private Doctor idDoc;


    public SocialDetails(){

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


}