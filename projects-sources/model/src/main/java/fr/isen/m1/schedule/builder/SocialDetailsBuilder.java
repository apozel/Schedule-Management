package fr.isen.m1.schedule.builder;

import java.util.Date;
import fr.isen.m1.schedule.utilities.SocialDetails;

/**
 * SocialDetailsBuilder
 */
public class SocialDetailsBuilder {

    private String firstName;
    private String lastName;
    private boolean gender;
    private String birthAddress;
    private String birthZipCode;
    private Date birthDate;
    private String phoneNumber;
    private String picture;

    public String getFirstName() {
        return firstName;
    }

    public SocialDetailsBuilder setFirstName(String nom) {
        this.firstName = nom;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SocialDetailsBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public boolean isGender() {
        return gender;
    }

    public SocialDetailsBuilder setGender(boolean gender) {
        this.gender = gender;
        return this;
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public SocialDetailsBuilder setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
        return this;
    }

    public String getBirthZipCode() {
        return birthZipCode;
    }

    public SocialDetailsBuilder setBirthZipCode(String birthZipCode) {
        this.birthZipCode = birthZipCode;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public SocialDetailsBuilder setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SocialDetailsBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public SocialDetailsBuilder setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public SocialDetails build(){
        SocialDetails details = new SocialDetails();
        details.setFirstName(firstName);
        details.setLastName(lastName);
        details.setBirthAddress(birthAddress);
        details.setBirthDate(birthDate);
        details.setBirthZipCode(birthZipCode);
        details.setGender(gender);
        details.setPhoneNumber(phoneNumber);
        details.setPicture(picture);
        return details;
    }


}